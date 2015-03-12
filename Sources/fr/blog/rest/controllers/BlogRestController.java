/**
 * 
 */
package fr.blog.rest.controllers;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

import com.google.inject.Inject;
import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.appserver.WOResponse;

import er.rest.routes.jsr311.DELETE;
import er.rest.routes.jsr311.GET;
import er.rest.routes.jsr311.POST;
import er.rest.routes.jsr311.PUT;
import er.rest.routes.jsr311.Path;
import er.rest.routes.jsr311.PathParam;
import fr.blog.beans.AutheurBean;
import fr.blog.beans.BlogBean;
import fr.blog.exceptions.RestNoDataException;
import fr.blog.rest.RestServiceDescription;
import fr.blog.service.AutheurService;
import fr.blog.service.BlogService;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 * 
 */
public class BlogRestController extends RestController {

	private static Logger LOG = Logger.getLogger(BlogRestController.class);

	@Inject
	private BlogService blogService;
	
	@Inject
	private AutheurService autheurService;

	/**
	 * @param request
	 */
	public BlogRestController(WORequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	//Blog
	
	/**
	 * Cherche les blogs à partir du token passé en paramètres
	 * @param token la chaine de recherche
	 * @return les résultats
	 */

	/**
	 * 
	 * @param token
	 *            le token à rechercher
	 * @return
	 */
	@GET
	@Path("/blog/recherche/{token:String}")
	public WOActionResults rechercheBlogAction(@PathParam("token") String token) {
		return jsonResponseWithObject(blogService.search(token));
	}

	/**
	 * Cherche un blog en particulier à partir de l'id du blog passé en paramètres
	 * @param blogId
	 * @return un resultat
	 */
	@GET
	@Path("/blog/numero/{blogId:Integer}")
	public WOActionResults blogParIdAction(
			@PathParam("blogId") Integer blogId) throws RestNoDataException {
		return jsonResponseWithObject(blogService.blogById(blogId));
	}
	
	/** 
	 * Create new blog
	 * **/
	@POST
	@Path("/blog/create/newBlog")
	public WOActionResults newBlogAction(BlogBean blogbean) {
		return jsonResponseWithObject(blogService.createNewBlog(blogbean));
	}
	
	/**Update blog **/
	@PUT
	@Path("/blog/update/{blogId:Integer}")
	public WOActionResults updateBlogAction(BlogBean blogbean) {
		return jsonResponseWithObject(blogService.updateBlog(blogbean));
	}
	
	/**Remove blog **/
	@DELETE
    @Path("/blog/delete/{blogId:Integer}")
	public void removeBlogAction(BlogBean blogbean) {
		blogService.removeBlog(blogbean);
	}
	
	//Autheur
	
	/**
	 * Cherche les blogs à partir du token passé en paramètres
	 * @param token la chaine de recherche
	 * @return les résultats
	 */
	@GET
	@Path("/autheur/recherche/{token:String}")
	public WOActionResults rechercheAutheurAction(@PathParam("token") String token)
			throws RestNoDataException {
		return jsonResponseWithObject(autheurService.search(token));
	}

	/**
	 * Revoie les détail d'un autheur de blog
	 * @param autheurId identifiant de l'autheur
	 * @return les details 
	 * @throws RestNoDataException 
	 */
	@GET
	@Path("/autheur/numero/{autheurId:Integer}")
	public WOActionResults autheurByIdAction(@PathParam("autheurId") Integer autheurId)
			throws RestNoDataException {
		return jsonResponseWithObject(autheurService.autheurById(autheurId));
	}
	
	@POST
	@Path("/autheur/create/newAutheur")
	public WOActionResults newAutheurAction(AutheurBean autheurbean) {
		return jsonResponseWithObject(autheurService.createNewAutheur(autheurbean));
	}
	@PUT
	@Path("/autheur/update/{autheurId:Integer}")
	public WOActionResults updateAutheurAction(AutheurBean autheurbean) {
		return jsonResponseWithObject(autheurService.updateAutheur(autheurbean));
	}
	
	@DELETE
    @Path("/autheur/delete/{autheurId:Integer}")
	public void removeAutheurAction(AutheurBean autheurbean) {
		autheurService.removeAutheur(autheurbean);
	}

	protected WOResponse jsonResponseWithObject(Object object) {
		WOResponse response = new WOResponse();
		response.setStatus(WOResponse.HTTP_STATUS_OK);
		response.setContentEncoding("UTF8");
		response.setHeader("BlogRest", "title");
		response.setHeader("*", "Access-Control-Allow-Origin");
		response.setHeader("application/json; charset=utf-8", "Content-Type");

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.INDENT_OUTPUT, true);
		try {
			response.setContent(mapper.writeValueAsString(object));
		} catch (Exception e) {
			LOG.error(e);
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.blog.rest.controllers.RestController#getServicesList()
	 */
	@Override
	public Collection<RestServiceDescription> getServicesList() {
		// TODO Auto-generated method stub
		return null;
	}

}
