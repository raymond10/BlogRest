/**
 * 
 */
package fr.blog.rest.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOApplication;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.foundation.ERXExceptionUtilities;
import er.extensions.foundation.ERXStringUtilities;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteController;
import fr.blog.Application;
import fr.blog.exceptions.IRestAuthException;
import fr.blog.exceptions.IRestException;
import fr.blog.rest.Auth;
import fr.blog.rest.AuthenticationService;
import fr.blog.rest.IRestParamBean;
import fr.blog.rest.IRestRequete;
import fr.blog.rest.RestServiceDescription;
import fr.blog.rest.log.RestLoggerFormat;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
/**
 * Classe abstraite qui doit être étendue par tous les controllers REST
 */
public abstract class RestController extends ERXRouteController {
	
	private static final Logger LOG = Logger.getLogger(RestController.class);
	
	protected static final Application REST_APP = (Application) WOApplication.application();
	public static final String AUTH_KEY = "cktlrestauthkey";
	public static final String HEADER_AUTH_KEY = "x-" + AUTH_KEY;

	private static final String KEY_EMPTY_VAL = WORequest._IsmapCoords;

	private Auth auth;

	/**
	 * @param request
	 */
	public RestController(WORequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected WOActionResults performActionNamedWithError(String actionName, Throwable t) {
		Throwable ex = ERXExceptionUtilities.getMeaningfulThrowable(t);
		LOG.debug("performActionNamedWithError : Action=" + actionName + " : " + ex.getMessage() + " : "
				+ ex.getCause());
		if (ex instanceof IRestException) {
			int errorCode = ERXHttpStatusCodes.BAD_REQUEST;
			if (ex instanceof IRestAuthException) {
				errorCode = ERXHttpStatusCodes.UNAUTHORIZED;
			}
			return restErrorResponse(ex, actionName, errorCode);
		}
		return super.performActionNamedWithError(actionName, ex);
	}

	private WOActionResults restErrorResponse(Throwable t, String actionName, int statusCode) {
		return errorResponse(actionName + " : " + ((IRestException) t).getErrorCode() + " : " + t.getMessage(),
				statusCode);
	}

	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.json();
	}

	/**
	 * @param context
	 *            Un {@link WOContext}
	 * @return L'URL complète du service REST du type <code>http(s)://server/.../Application.woa/ra/</code>
	 */
	public String getServiceUrl(WOContext context) {
		String appInstanceURL = REST_APP.getApplicationInstanceURL(context);
		StringBuffer actionUrl = new StringBuffer(appInstanceURL);
		if (!appInstanceURL.endsWith("/")) {
			actionUrl.append("/");
		}
		actionUrl.append("ra/");
		return actionUrl.toString();
	}

	/**
	 * Vérifie que l'utilisateur est authentifié
	 * 
	 * @param resourceId
	 *            Le libellé de la ressource
	 */
	public void checkUserAccess(String resourceId) {
		setAuth(new AuthenticationService().checkAccess(getAuthKey(), resourceId));
	}

	protected String getAuthKey() {
		String authKey = (String) request().formValueForKey(AUTH_KEY);
		if (ERXStringUtilities.stringIsNullOrEmpty(authKey)) {
			authKey = request().headerForKey(HEADER_AUTH_KEY);
		}
		if (ERXStringUtilities.stringIsNullOrEmpty(authKey)) {
			authKey = request().cookieValueForKey(AUTH_KEY);
		}
		return authKey;
	}

	/**
	 * @return La liste des services disponibles
	 */
	public abstract Collection<RestServiceDescription> getServicesList();

	protected String getRoutePath() {
		return request().uri().substring(request().uri().indexOf("/ra") + 3);
	}

	/**
	 * @return une map construite à partir des parametres passes dans l'url
	 */
	protected Map<String, List<Object>> getRestParams() {
		NSDictionary<String, NSArray<Object>> formValues = request().formValues();
		Map<String, List<Object>> restParams = new HashMap<String, List<Object>>();

		for (String aKey : formValues.allKeys()) {
			if (KEY_EMPTY_VAL.equals(aKey)) {
				List<Object> list = formValues.get(aKey);
				for (Object object : list) {
					restParams.put(object.toString(), null);
				}
			} else {
				restParams.put(aKey, formValues.get(aKey));
			}
		}
		return restParams;
	}

	protected Map<String, List<Object>> getRestPreparedParams() {
		Map<String, List<Object>> restPreparedParams = new HashMap<String, List<Object>>();
		restPreparedParams.putAll(getRestParams());
		if (getAuth() != null) {
			restPreparedParams.put(Auth.UTILISATEUR_PERSID_PARAMKEY, Collections.singletonList((Object) getAuth().getPersId()));
		}
		return restPreparedParams;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	protected String prepareLogMessage() {
		String logMsg = RestLoggerFormat.formatLog(request(), route().method().name(), getRoutePath(),
				getRestPreparedParams());
		return logMsg;
	}

	protected RestServiceDescription getServiceDescription(IRestRequete iRestRequete, Collection<? extends IRestParamBean> params, String route) {
		RestServiceDescription serviceDescription = new RestServiceDescription();
		serviceDescription.setCategorie(iRestRequete.categorie());
		serviceDescription.setCommentaireDeveloppeur(iRestRequete.commentaireDeveloppeur());
		serviceDescription.setCommentaireUtilisateur(iRestRequete.commentaireUtilisateur());
		serviceDescription.setLibelle(iRestRequete.libelle());
		serviceDescription.setStrId(iRestRequete.strId());
		serviceDescription.setParams(params);
		serviceDescription.setRoute(route);
		return serviceDescription;
	}

}
