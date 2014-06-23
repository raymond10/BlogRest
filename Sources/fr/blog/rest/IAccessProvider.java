/**
 * 
 */
package fr.blog.rest;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
public interface IAccessProvider {
	/**
	 * V�rifie si l'utilisateur a le droit de se connecter � la ressource.
	 * 
	 * @param auth Objet contenant les infos d'autehntification
	 * @param resourceId Identifiant de la ressource � laquelle on veut acc�der
	 * @throws SecurityException si l'utilisateur a le droit de se connecter � la ressource.
	 */
	void checkAccess(Auth auth, String resourceId) throws SecurityException;
}
