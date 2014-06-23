/**
 * 
 */
package fr.blog.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.foundation.NSArray;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;
import fr.blog.metier.Autheur;
import fr.blog.metier.Blog;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 24 févr. 2014
 * 
 */
public class BlogController extends BaseRestController {

	/**
	 * @param request
	 */
	public BlogController(WORequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}

	public WOActionResults createAction() throws Throwable {
		Blog blog = create(filter());
		editingContext().saveChanges();
		return response(blog, filter());
	}

	@Override
	public WOActionResults updateAction() {
		Blog blog = blog();
		update(blog, updateFilter());
		editingContext().saveChanges();
		return response(blog, filter());
	}

	/**
	 * lockedUpdate is just like update except that the "company" relationships
	 * is locked, which means that you can update the object on the other side
	 * of the relationship, but you can't change the related object itself.
	 */
	public WOActionResults lockedUpdateAction() {
		Blog blog = blog();
		ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
		filter.include(Blog.AUTHEUR).includeAttributes(); // let you update a
															// company inside of
															// a blog
		filter.lockRelationship(Blog.AUTHEUR); // don't let you change the
												// company relationship
		update(blog, filter);
		editingContext().saveChanges();
		return response(blog, filter());
	}

	/**
	 * securityUpdate is just like a regular update except that it will not let
	 * you change the blog's autheur name to Microsoft when updating the Blog
	 * using the ERXKeyFilter.Delegate API
	 */
	public WOActionResults securityUpdateAction() {
		Blog blog = blog();
		ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
		filter.include(Blog.AUTHEUR).includeAttributes(); // let you update a
															// autheur inside of
															// a blog
		filter.setDelegate(new ERXKeyFilter.Delegate() {
			public void willTakeValueForKey(Object target, Object value,
					String key) throws SecurityException {
				if (target instanceof Autheur && "name".equals(key)
						&& value != null
						&& ((String) value).contains("Microsoft")) {
					throw new SecurityException(
							"You can't change a Blog's autheur name to Microsoft.");
				}
			}

			public void didTakeValueForKey(Object target, Object value,
					String key) throws SecurityException {
			}

			public void didSkipValueForKey(Object target, Object value,
					String key) throws SecurityException {
			}
		});
		update(blog, filter);
		editingContext().saveChanges();
		return response(blog, filter());
	}

	public WOActionResults indexAction() throws Throwable {
		NSArray<Blog> blogs = Blog.fetchAllBlogs(editingContext());
		return response(blogs, filter());
	}

	@Override
	public WOActionResults newAction() throws Throwable {
		Blog blog = Blog.createBlog(editingContext(), new Autheur());
		return response(blog, filter());
	}

	@Override
	public WOActionResults destroyAction() throws Throwable {
		return errorResponse(ERXHttpStatusCodes.METHOD_NOT_ALLOWED);
	}

	@Override
	public WOActionResults showAction() throws Throwable {
		return response(blog(), filter());
	}

	// Filtre affichant le detail sur un autheur
	protected ERXKeyFilter filter() {
		ERXKeyFilter blogFilter = ERXKeyFilter.filterWithAttributes();
		blogFilter.setAnonymousUpdateEnabled(true);
		ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
		filter.include(Blog.AUTHEUR, blogFilter);
		filter.setUnknownKeyIgnored(true);

		return filter;
	}

	public static ERXKeyFilter updateFilter() {
		ERXKeyFilter filter = ERXKeyFilter.filterWithAttributes();
		filter.include(Blog.AUTHEUR).includeAttributes(); // let you update a
															// autheur inside of
															// a blog
		return filter;
	}

	@Override
	protected boolean isAutomaticHtmlRoutingEnabled() {
		return true;
	}

	protected Blog blog() {
		Blog blog = routeObjectForKey("blog");
		return blog;
	}

}
