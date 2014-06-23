/**
 * 
 */
package fr.blog.rest.controllers;

import java.util.ArrayList;
import java.util.Collection;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.eof.ERXKeyFilter;
import er.rest.routes.jsr311.GET;
import er.rest.routes.jsr311.Path;
import fr.blog.rest.RestServiceDescription;
import fr.blog.rest.RestServicesConfig;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class ServicesListController extends RestController {

	private static final String SERVICES_ROUTE = "services";

	private static final RestServiceDescription SERVICE_DESCRIPTION = new RestServiceDescription();
	static {
		SERVICE_DESCRIPTION.setRoute(SERVICES_ROUTE);
		SERVICE_DESCRIPTION.setLibelle("Liste des services");
	}

	/**
	 * @param request Une {@link WORequest}
	 */
	public ServicesListController(WORequest request) {
		super(request);
	}

	/**
	 * @return Une réponse contenant la liste des services
	 */
	@GET
	@Path("/" + SERVICES_ROUTE)
	public WOActionResults indexAction() {
		RestServicesConfig.restServicesLogger().info(prepareLogMessage());
		Collection<RestServiceDescription> liste = getServicesList();
		return response(liste, ERXKeyFilter.filterWithAll());
	}

	/**
	 * @return La liste des services sous forme d'URL
	 * @see ServicesListController#getServiceUrl(com.webobjects.appserver.WOContext)
	 */
	/*
	 * public Collection<String> getServicesListAsUrl() { Collection<String> liste = new ArrayList<String>(); String url = getServiceUrl(context());
	 * for (String service : getServicesList()) { liste.add(url + service); } return liste; }
	 */

	@Override
	public Collection<RestServiceDescription> getServicesList() {
		Collection<RestServiceDescription> liste = new ArrayList<RestServiceDescription>();
		liste.add(serviceDescription());
		//liste.addAll(new AuthController(request()).getServicesList());
		//liste.addAll(new QueryController(request()).getServicesList());

		return liste;
	}

	public RestServiceDescription serviceDescription() {
		return SERVICE_DESCRIPTION;
	}

}
