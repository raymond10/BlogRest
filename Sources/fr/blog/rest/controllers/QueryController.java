/**
 * 
 */
package fr.blog.rest.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.eof.ERXKey;
import er.extensions.eof.ERXKeyFilter;
import er.rest.routes.jsr311.GET;
import er.rest.routes.jsr311.Path;
import er.rest.routes.jsr311.PathParam;
import fr.blog.exceptions.RestNoDataException;
import fr.blog.exceptions.RestParseException;
import fr.blog.rest.FinderRestSQLRequete;
import fr.blog.rest.IDataProvider;
import fr.blog.rest.IRestParamBean;
import fr.blog.rest.IRestRequete;
import fr.blog.rest.RestServiceDescription;
import fr.blog.rest.RestServicesConfig;
import fr.blog.rest.SQLDataProvider;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
@SuppressWarnings("all")
public class QueryController extends RestController {

	private static final String QUERYID_KEY = "queryId";
	public static final String QUERYCLASS = "query";
	public static final String QUERY_ROUTE_PATH = "/" + QUERYCLASS + "/{" + QUERYID_KEY + ":String}/list";
	public static final String QUERY_COUNT_ROUTE_PATH = "/" + QUERYCLASS + "/{" + QUERYID_KEY + ":String}/count";
	public static final String QUERY_SCHEMA_ROUTE_PATH = "/" + QUERYCLASS + "/{" + QUERYID_KEY + ":String}/doc";

	/**
	 * The data provider. TODO Google Guice
	 */
	private IDataProvider dataProvider = new SQLDataProvider();

	/**
	 * @param request Une {@link WORequest}
	 */
	public QueryController(WORequest request) {
		super(request);
	}

	/**
	 * @param queryId
	 *            L'identifiant de la requête.
	 * @return Les données correspondantes.
	 * @throws RestNoDataException
	 *             Si la requête n'a pas été trouvée.
	 * @throws RestParseException
	 *             Si erreur lors du traitement de la requête.
	 * 
	 */
	@GET
	@Path(QUERY_ROUTE_PATH)
	public WOActionResults listAction(@PathParam(QUERYID_KEY) String queryId) throws RestNoDataException, RestParseException {
		checkUserAccess(queryId);
		String logMsg = prepareLogMessage();

		Collection<Map<String, Object>> res = null;
		//res = dataProvider.getData(queryId, getRestPreparedParams());
		res = new NSMutableArray<Map<String,Object>>(); 
		logMsg += " | OK";
		RestServicesConfig.restServicesLogger().info(logMsg);
		return response(res, ERXKeyFilter.filterWithAll());
	}

	/**
	 * @param queryId
	 *            L'identifiant de la requête.
	 * @return Le nombre de données correspondantes.
	 * @throws RestNoDataException
	 *             Si la requête n'a pas été trouvée.
	 * @throws RestParseException
	 *             Si erreur lors du traitement de la requête.
	 * 
	 */
	@GET
	@Path(QUERY_COUNT_ROUTE_PATH)
	public WOActionResults countAction(@PathParam(QUERYID_KEY) String queryId) throws RestNoDataException, RestParseException {
		checkUserAccess(queryId);
		String logMsg = prepareLogMessage();
		
		Long res;
		//res = dataProvider.count(queryId, getRestPreparedParams());
		res = new Long(0);
		logMsg += " | OK";
		RestServicesConfig.restServicesLogger().info(logMsg);
		return response(res, ERXKeyFilter.filterWithAll());
	}
	
	/**
	 * @param filterId
	 *            L'identifiant de la requête.
	 * @return La description de la requête
	 * @throws RestNoDataException
	 *             Si la requête n'a pas été trouvée.
	 */
	@GET
	@Path(QUERY_SCHEMA_ROUTE_PATH)
	public WOActionResults docAction(@PathParam(QUERYID_KEY) String filterId) throws RestNoDataException {
		//IRestRequete iRestRequete = FinderRestSQLRequete.instance().findByStringId(ERXEC.newEditingContext(), filterId);
		IRestRequete iRestRequete = new FinderRestSQLRequete();
		//Collection<? extends IRestParamBean> params = dataProvider.getParamsForQuery(iRestRequete);
		Collection<? extends IRestParamBean> params = new NSMutableArray<IRestParamBean>();
		String route = QUERYCLASS + "/" + iRestRequete.strId() + "/list";
		RestServiceDescription serviceDescription = getServiceDescription(iRestRequete, params, route);

		String logMsg = prepareLogMessage();
		RestServicesConfig.restServicesLogger().info(logMsg);

		ERXKeyFilter filter = ERXKeyFilter.filterWithAllRecursive();
		filter.exclude(new ERXKey<String>("params.formattedValue"));
		filter.exclude(new ERXKey<String>("params.rawName"));

		return response(serviceDescription, filter);
	}

	@Override
	public Collection<RestServiceDescription> getServicesList() {
		List<RestServiceDescription> serviceList = new ArrayList<RestServiceDescription>();
		//Collection<? extends IRestRequete> listeQuery = dataProvider.getAvailableQueries();
		Collection<? extends IRestRequete> listeQuery = new NSMutableArray<IRestRequete>();
		for (IRestRequete iRestRequete : listeQuery) {
			//Collection<? extends IRestParamBean> params = dataProvider.getParamsForQuery(iRestRequete);
			Collection<? extends IRestParamBean> params = new NSMutableArray<IRestParamBean>();
			String route = QUERYCLASS + "/" + iRestRequete.strId() + "/list";
			RestServiceDescription serviceDescription = getServiceDescription(iRestRequete, params, route);
			String countRoute = QUERYCLASS + "/" + iRestRequete.strId() + "/count";
			serviceDescription.setCountRoute(countRoute);
			serviceList.add(serviceDescription);
		}
		Collections.sort(serviceList);
		return serviceList;
	}

}
