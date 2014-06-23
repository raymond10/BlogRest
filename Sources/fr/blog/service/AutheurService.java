/**
 * 
 */
package fr.blog.service;

import java.util.List;
import fr.blog.beans.AutheurBean;
import fr.blog.exceptions.RestNoDataException;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 28 f�vr. 2014
 *
 */
public interface AutheurService {
	
	/**
	 * Cherche les blogs � partir du token pass� en param�tres
	 * @param token la chaine de recherche
	 * @return les r�sultats
	 */
	List<AutheurBean> search(String token) throws RestNoDataException;;
	
	/**
	 * Revoie les d�tail d'un autheur de blog
	 * @param autheurId identifiant de l'autheur
	 * @return les details 
	 * @throws RestNoDataException 
	 */
	AutheurBean autheurById(Integer autheurId) throws RestNoDataException;
	
	
	AutheurBean createNewAutheur(AutheurBean autheur);
	
	AutheurBean updateAutheur(AutheurBean autheur);
	/**Delete Autheur **/
	void removeAutheur(AutheurBean autheur);
}
