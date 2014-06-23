/**
 * 
 */
package fr.blog.rest;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public interface IRestRequete {

	public String categorie();

	public String commentaireDeveloppeur();

	public String commentaireUtilisateur();

	public String libelle();

	/**
	 * @return Un id qui sert à identifier le requete de maniere unique.
	 */
	public String strId();

}
