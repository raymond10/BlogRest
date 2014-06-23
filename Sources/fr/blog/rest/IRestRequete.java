/**
 * 
 */
package fr.blog.rest;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
public interface IRestRequete {

	public String categorie();

	public String commentaireDeveloppeur();

	public String commentaireUtilisateur();

	public String libelle();

	/**
	 * @return Un id qui sert � identifier le requete de maniere unique.
	 */
	public String strId();

}
