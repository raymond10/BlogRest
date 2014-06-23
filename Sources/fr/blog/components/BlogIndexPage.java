package fr.blog.components;

import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.components.ERXComponent;
import er.extensions.eof.ERXEC;
import er.rest.routes.IERXRouteComponent;
import fr.blog.metier.Blog;

public class BlogIndexPage extends ERXComponent implements IERXRouteComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1845066520680853172L;

	private Blog blogItem;

	public BlogIndexPage(WOContext context) {
		super(context);
	}

	public NSArray<Blog> blogs() {
		EOEditingContext ec = ERXEC.newEditingContext();
		return Blog.fetchAllBlogs(ec, Blog.DATE_CREATION.descs());
	}

	public Blog blogItem() {
		return blogItem;
	}

	public void setBlogItem(Blog blogItem) {
		this.blogItem = blogItem;
	}
}