package fr.blog;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.ArrayUtils;

import com.google.inject.Module;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOMessage;
import com.woinject.InjectableApplication;
import com.woinject.WOInject;

import er.extensions.appserver.ERXApplication;
import er.extensions.appserver.ERXMessageEncoding;
import er.extensions.foundation.ERXProperties;
import er.rest.routes.ERXRoute;
import er.rest.routes.ERXRouteRequestHandler;
import fr.blog.metier.Blog;
import fr.blog.rest.controllers.BlogController;
import fr.blog.rest.controllers.BlogRestController;
import fr.blog.rest.controllers.ServicesListController;
import fr.blog.service.Services;

public class Application extends InjectableApplication {
	

	private static final String ER_REST_TIMESTAMP_FORMAT = "er.rest.timestampFormat";
	private static final String ER_REST_TIMESTAMP_DEFAULT_FORMAT = "%d/%m/%Y";
	private static String defaultAppURL;
	
	public static void main(String[] argv) {
		//ERXApplication.main(argv, Application.class);
		WOInject.init("fr.blog.Application", argv);
	}

	public Application() {
		ERXApplication.log.info("Lancement de l'application serveur " + name() + " !");
		/* ** put your initialization code in here ** */
		setAllowsConcurrentRequestHandling(true);
		// Formatage des dates dans les rŽponses REST
		String dateFormat = ERXProperties.stringForKeyWithDefault(ER_REST_TIMESTAMP_FORMAT, ER_REST_TIMESTAMP_DEFAULT_FORMAT);
		ERXProperties.setStringForKey(dateFormat, ER_REST_TIMESTAMP_FORMAT);

		ERXProperties.systemPropertiesChanged();
		setAllowsConcurrentRequestHandling(false);
		setDefaultRequestHandler(requestHandlerForKey(directActionRequestHandlerKey()));
		setPageRefreshOnBacktrackEnabled(true);
		WOMessage.setDefaultEncoding("UTF-8");
		WOMessage.setDefaultURLEncoding("UTF-8");
		ERXMessageEncoding.setDefaultEncoding("UTF8");
		ERXMessageEncoding.setDefaultEncodingForAllLanguages("UTF8");
	}
	
	public void finishInitialization() {
		ERXRouteRequestHandler routeRequestHandler = new ERXRouteRequestHandler(ERXRouteRequestHandler.WO);
		routeRequestHandler.addDefaultRoutes(Blog.ENTITY_NAME);
		routeRequestHandler.addRoutes(BlogRestController.class);
		ERXRouteRequestHandler.register(routeRequestHandler);
		System.out.println(routeRequestHandler.routes());
	}
	
	protected Module[] modules() {
		return ArrayUtils.add(super.modules(), new RestMoldule());
	}

	public String getApplicationURL(WOContext context) {
		String applicationURL;
		// Si le context est disponible, on l'utilise pour determiner l'url
		if (context != null) {
			if (context.request().isUsingWebServer())
				applicationURL = webserverConnectURL();
			else
				applicationURL = directConnectURL();
		} else { // Sinon, on essaie de le trouver...
					// ...en testant si WebConnect repond
			if (defaultAppURL == null) {
				defaultAppURL = webserverConnectURL();
				// ...sinon, on prend direct connect
				if (!checkConnection(defaultAppURL))
					defaultAppURL = directConnectURL();
			}
			applicationURL = defaultAppURL;
		}
		// On ellimine le tout ce qui suit ".woa/"
		int i = applicationURL.indexOf(".woa");
		if (i >= 0)
			applicationURL = applicationURL.substring(0, i) + ".woa";
		//    // Sinon, on cherche le URL dans la config de l'application
		//    if ((applicationURL == null) ||
		//        (!applicationURL.startsWith("http")))
		//      applicationURL = config().stringForKey("APP_URL");
		return applicationURL;
	}

	/**
	 * Retourne le URL de connexion a l'instance en cours d'execution. Cette methode retourne le meme URL que la methode
	 * {@link #getApplicationURL(WOContext)}, mais complete par le numero d'instance, si possible.
	 * 
	 * @see #getApplicationURL(WOContext)
	 */
	public String getApplicationInstanceURL(WOContext context) {
		// On prend d'abord le URL par defaut
		String applicationURL = getApplicationURL(context);
		String applicationInstanceURL = applicationURL;
		// Le URL par defaut ne tien pas compte de numero d'instance
		// On essaye de l'ajouter
		if ((applicationURL != null) && (context != null)) {
			// Le numero de l'instance peut se trouver dans URL apres ".woa"
			String req = context.request().applicationURLPrefix();
			int i = req.indexOf(".woa");
			if (i >= 0)
				req = req.substring(0, i) + ".woa";
			i = applicationURL.indexOf(req);
			if (i >= 0) {
				applicationInstanceURL = applicationURL.substring(0, i);
				req = context.request().applicationURLPrefix();
				if (!req.startsWith("/") && (applicationInstanceURL.length() > 0))
					applicationInstanceURL += "/";
				applicationInstanceURL += req;
			}
		}
		return applicationInstanceURL;
	}

	/**
	 * Verifie si la connexion a l'adresse <code>link</code> fonctionne. Cette methode essaie d'etablire une connexion a l'adresse donnee et retourne
	 * <code>true</code> dans le cas si la connexion repond, sinon <code>false</code>.
	 */
	private boolean checkConnection(String link) {
		try {
			URL url = new URL(link);
			URLConnection con = url.openConnection();
			con.connect();
			return true;
		} catch (Exception e) {
			//      e.printStackTrace();
			return false;
		}
	}
}