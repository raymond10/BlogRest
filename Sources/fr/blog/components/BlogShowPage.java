package fr.blog.components;

import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;
import er.rest.routes.ERXRouteParameter;
import er.rest.routes.IERXRouteComponent;
import fr.blog.metier.Blog;

public class BlogShowPage extends ERXComponent implements IERXRouteComponent {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3781088974431804491L;
	private Blog blog
;

	public BlogShowPage(WOContext context) {
        super(context);
    }
	
	@ERXRouteParameter
    public void setBlog(Blog blogFromController) {
      this.blog = blogFromController;
    }
 
    public Blog blog() {
      return this.blog;
    }
}