/**
 * 
 */
package fr.blog.beans.Impl;

import com.webobjects.foundation.NSTimestamp;

import fr.blog.beans.BlogBean;
import fr.blog.metier.Blog;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class BlogBeanImpl implements BlogBean {
	
	private Integer Id;
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
		Id = blog.blogId();
		autheurId = blog.autheurId();
		autheur = blog.autheur().fullName();
		titre = blog.titre();
		contenu = blog.contenu();
		dateCreation = blog.dateCreation();
		derniereModif = blog.derniereModif();
	}
	
	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getBlogId()
	 */
	@Override
	public Integer getBlogId() {
		// TODO Auto-generated method stub
		return Id;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setBlogId(java.lang.Integer)
	 */
	@Override
	public void setBlogId(Integer Id) {
		// TODO Auto-generated method stub
		this.Id = Id;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getAutheurId()
	 */
	@Override
	public Integer getAutheurId() {
		// TODO Auto-generated method stub
		return autheurId;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setAutheurId(java.lang.Integer)
	 */
	@Override
	public void setAutheurId(Integer autheurId) {
		// TODO Auto-generated method stub
		this.autheurId = autheurId;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getTitre()
	 */
	@Override
	public String getTitre() {
		// TODO Auto-generated method stub
		return titre;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setTitre(java.lang.String)
	 */
	@Override
	public void setTitre(String titre) {
		// TODO Auto-generated method stub
		this.titre = titre;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getContenu()
	 */
	@Override
	public String getContenu() {
		// TODO Auto-generated method stub
		return contenu;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setContenu(java.lang.String)
	 */
	@Override
	public void setContenu(String contenu) {
		// TODO Auto-generated method stub
		this.contenu = contenu;
		
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getDateCreation()
	 */
	@Override
	public NSTimestamp getDateCreation() {
		// TODO Auto-generated method stub
		return dateCreation;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setDateCreation(com.webobjects.foundation.NSTimestamp)
	 */
	@Override
	public void setDateCreation(NSTimestamp dateCreation) {
		// TODO Auto-generated method stub
		this.dateCreation = dateCreation;
		
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getDerniereModif()
	 */
	@Override
	public NSTimestamp getDerniereModif() {
		// TODO Auto-generated method stub
		return derniereModif;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setDerniereModif(com.webobjects.foundation.NSTimestamp)
	 */
	@Override
	public void setDerniereModif(NSTimestamp derniereModif) {
		// TODO Auto-generated method stub
		this.derniereModif = derniereModif;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#getAutheur()
	 */
	@Override
	public String getAutheur() {
		// TODO Auto-generated method stub
		return autheur;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogSearchBean#setAutheur(java.lang.String)
	 */
	@Override
	public void setAutheur(String autheur) {
		// TODO Auto-generated method stub
		this.autheur = autheur;
	}

}
