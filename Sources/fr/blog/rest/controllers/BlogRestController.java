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

	@GET
	@Path("/blog/{blogId:Integer}")
	public WOActionResults blogParIdAction(
			@PathParam("blogId") Integer blogId) throws RestNoDataException {
		return jsonResponseWithObject(blogService.blogById(blogId));
	}
	
	@POST
	@Path("/blog/newBlog")
	public WOActionResults newBlogAction(BlogBean blogbean) {
		return jsonResponseWithObject(blogService.createNewBlog(blogbean));
	}
	@PUT
	@Path("/blog/update/{blogId:Integer}")
	public WOActionResults updateBlogAction(BlogBean blogbean) {
		return jsonResponseWithObject(blogService.updateBlog(blogbean));
	}
	
	@DELETE
    @Path("/blog/delete/{blogId:Integer}")
	public void removeBlogAction(BlogBean blogbean) {
		blogService.removeBlog(blogbean);
	}
	
	//Autheur
	@GET
	@Path("/autheur/recherche/{token:String}")
	public WOActionResults rechercheAutheurAction(@PathParam("token") String token)
			throws RestNoDataException {
		return jsonResponseWithObject(autheurService.search(token));
	}

	@GET
	@Path("/autheur/{autheurId:Integer}")
	public WOActionResults autheurByIdAction(@PathParam("autheurId") Integer autheurId)
			throws RestNoDataException {
		return jsonResponseWithObject(autheurService.autheurById(autheurId));
	}
	
	@POST
	@Path("/autheur/newAutheur")
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
		response.setHeader("application/json", "Content-Type");

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
