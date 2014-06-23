/**
 * 
 */
package fr.blog.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import er.extensions.localization.ERXLocalizer;
import fr.blog.beans.AutheurBean;
import fr.blog.beans.BlogBean;
import fr.blog.beans.Impl.AutheurBeanImpl;
import fr.blog.beans.Impl.BlogBeanImpl;
import fr.blog.exceptions.RestNoDataException;
import fr.blog.metier.Autheur;
import fr.blog.metier.Blog;
import fr.blog.service.BlogService;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 * 
 */

@SuppressWarnings("all")
public class BlogServiceImpl implements BlogService {

	private EOEditingContext edc = ERXEC.newEditingContext();

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.blog.service.BlogSearchService#search(java.lang.String)
	 */
	@Override
	public List<BlogBean> search(String token) {
		// TODO Auto-generated method stub
		List<BlogBean> beans = new ArrayList<BlogBean>();
		NSArray<Blog> results = Blog.fetchBlogs(edc, Blog.TITRE.contains(token)
				.or(Blog.CONTENU.contains(token)), Blog.TITRE.descs());
		for (Blog blog : results) {
			beans.add(new BlogBeanImpl(blog));
		}
		return beans;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.BlogService#blogById(java.lang.Integer)
	 */
	@Override
	public BlogBean blogById(Integer blogId) throws RestNoDataException {
		// TODO Auto-generated method stub
		Blog blog = Blog.fetchBlog(edc, Blog.BLOG_ID.eq(blogId));
		if(blog == null)
			throw new RestNoDataException(ERXLocalizer.defaultLocalizer()
					.localizedStringForKey("blog.nontrouve"));
		return new BlogBeanImpl(blog);
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.BlogService#createNewBlog(fr.blog.beans.BlogBean)
	 */
	@Override
	public BlogBean createNewBlog(BlogBean blogBean) {
		// TODO Auto-generated method stub
		EOEditingContext myCreateEdc = ERXEC.newEditingContext();
		Autheur autheur = Autheur.fetchAutheur(myCreateEdc, Autheur.AUTHEUR_ID.eq(blogBean.getAutheurId()));
		Blog blog = Blog.createBlog(myCreateEdc);
		blog.setTitre(blogBean.getTitre());
		blog.setContenu(blogBean.getContenu());
		blog.setAutheurRelationship(autheur);
		blog.setAutheurId(autheur.autheurId());
		blog.editingContext().saveChanges();
		blogBean.setBlogId(blog.blogId());
		blogBean.setAutheurId(blog.autheurId());
		blogBean.setAutheur(blog.autheur().fullName());
		blogBean.setDateCreation(blog.dateCreation());
		blogBean.setDerniereModif(blog.derniereModif());
		return blogBean;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.BlogService#updateBlog(fr.blog.beans.BlogBean)
	 */
	@Override
	public BlogBean updateBlog(BlogBean blogBean) {
		// TODO Auto-generated method stub
		EOEditingContext myUpdateEdc = ERXEC.newEditingContext();
		Autheur autheur = Autheur.fetchAutheur(myUpdateEdc, Autheur.AUTHEUR_ID.eq(blogBean.getAutheurId()));
		Blog blog = Blog.fetchBlog(myUpdateEdc, Blog.BLOG_ID.eq(blogBean.getBlogId()));
		blog.setTitre(blogBean.getTitre());
		blog.setContenu(blogBean.getContenu());
		blog.setAutheurRelationship(autheur);
		blog.setAutheurId(autheur.autheurId());
		blog.setDateCreation(blogBean.getDateCreation());
		blog.setDerniereModif(blogBean.getDerniereModif());
		blog.editingContext().saveChanges();
		return blogBean;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.BlogService#removeBlog(fr.blog.beans.BlogBean)
	 */
	@Override
	public void removeBlog(BlogBean blogBean) {
		// TODO Auto-generated method stub
		EOEditingContext myRemoveEdc = ERXEC.newEditingContext();
		Blog blog = Blog.fetchBlog(myRemoveEdc, Blog.BLOG_ID.eq(blogBean.getBlogId()));
		myRemoveEdc.deleteObject(blog);
		myRemoveEdc.saveChanges();
	}

}
