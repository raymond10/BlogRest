/**
 * 
 */
package fr.blog.service;

import java.util.List;
import fr.blog.beans.AutheurBean;
import fr.blog.exceptions.RestNoDataException;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 28 févr. 2014
 *
 */
public interface AutheurService {
	
	/**
	 * Cherche les blogs à partir du token passé en paramètres
	 * @param token la chaine de recherche
	 * @return les résultats
	 */
	List<AutheurBean> search(String token) throws RestNoDataException;;
	
	/**
	 * Revoie les détail d'un autheur de blog
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
