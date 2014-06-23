/**
 * 
 */
package fr.blog.rest;

import java.util.Collection;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestServiceDescription implements Comparable<RestServiceDescription> {
	private String strId;
	private String libelle;
	private String categorie;
	private String commentaireDeveloppeur;
	private String commentaireUtilisateur;
	private Collection<? extends IRestParamBean> params;
	private String route;
	private String countRoute;

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCommentaireDeveloppeur() {
		return commentaireDeveloppeur;
	}

	public void setCommentaireDeveloppeur(String commentaireDeveloppeur) {
		this.commentaireDeveloppeur = commentaireDeveloppeur;
	}

	public String getCommentaireUtilisateur() {
		return commentaireUtilisateur;
	}

	public void setCommentaireUtilisateur(String commentaireUtilisateur) {
		this.commentaireUtilisateur = commentaireUtilisateur;
	}

	public Collection<? extends IRestParamBean> getParams() {
		return params;
	}

	public void setParams(Collection<? extends IRestParamBean> params) {
		this.params = params;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String url) {
		this.route = url;
	}
	
	public void setCountRoute(String countRoute) {
		this.countRoute = countRoute;		
	}
	
	public String getCountRoute() {
		return this.countRoute;
	}

	/** {@inheritDoc} */
	public int compareTo(RestServiceDescription o) {
		return getLibelle().compareTo(o.getLibelle());
	}

}
