/**
 * 
 */
package fr.blog.rest;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public interface IAccessProvider {
	/**
	 * Vérifie si l'utilisateur a le droit de se connecter à la ressource.
	 * 
	 * @param auth Objet contenant les infos d'autehntification
	 * @param resourceId Identifiant de la ressource à laquelle on veut accéder
	 * @throws SecurityException si l'utilisateur a le droit de se connecter à la ressource.
	 */
	void checkAccess(Auth auth, String resourceId) throws SecurityException;
}
