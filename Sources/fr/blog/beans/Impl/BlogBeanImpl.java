/**
 * 
 */
package fr.blog.beans.Impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import com.webobjects.foundation.NSTimestamp;

import fr.blog.beans.BlogBean;
import fr.blog.metier.Blog;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class BlogBeanImpl implements BlogBean {
	
	private Integer blogId;
	private Integer autheurId;
	private String titre;
	private String contenu;
	private NSTimestamp dateCreation;
	private NSTimestamp derniereModif;
	private String autheur;
	
	/**
	 * 
	 */
	public BlogBeanImpl(Blog blog) {
		// TODO Auto-generated constructor stub
		blogId = blog.blogId();
		autheurId = blog.autheurId();
		autheur = blog.autheur().fullName();
		titre = blog.titre();
		contenu = blog.contenu();
		dateCreation = blog.dateCreation();
		derniereModif = blog.derniereModif();
	}
}
