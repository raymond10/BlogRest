/**
 * 
 */
package fr.blog.beans.Impl;

import java.util.List;

import fr.blog.beans.AutheurBean;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class AutheurBeanImpl implements AutheurBean {
	
	private Integer autheurId;
	private String nom;
	private String prenom;
	private String email;
	private List<String> blogs;

	/**
	 * 
	 */
	public AutheurBeanImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#getAutheurId()
	 */
	@Override
	public Integer getAutheurId() {
		// TODO Auto-generated method stub
		return autheurId;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#setAutheurId(java.lang.Integer)
	 */
	@Override
	public void setAutheurId(Integer Id) {
		// TODO Auto-generated method stub
		this.autheurId = Id;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#getNom()
	 */
	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#setNom(java.lang.String)
	 */
	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		this.nom = nom;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#getPrenom()
	 */
	@Override
	public String getPrenom() {
		// TODO Auto-generated method stub
		return prenom;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#setPrenom(java.lang.String)
	 */
	@Override
	public void setPrenom(String prenom) {
		// TODO Auto-generated method stub
		this.prenom = prenom;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#getEmail()
	 */
	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#getBlogs()
	 */
	@Override
	public List<String> getBlogs() {
		// TODO Auto-generated method stub
		return blogs;
	}

	/* (non-Javadoc)
	 * @see fr.blog.beans.BlogAutheurDetailBean#setBlogs(java.util.List)
	 */
	@Override
	public void setBlogs(List<String> blogs) {
		// TODO Auto-generated method stub
		this.blogs = blogs;
	}

}
