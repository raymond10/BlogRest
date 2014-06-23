/**
 * 
 */
package fr.blog.service;

import java.util.List;

import fr.blog.beans.BlogBean;
import fr.blog.exceptions.RestNoDataException;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public interface BlogService {
	
	/**
	 * Cherche les blogs à partir du token passé en paramètres
	 * @param token la chaine de recherche
	 * @return les résultats
	 */
	List<BlogBean> search(String token);
	
	/**
	 * Cherche un blog en particulier à partir de l'id du blog passé en paramètres
	 * @param blogId
	 * @return un resultat
	 */
	BlogBean blogById(Integer blogId) throws RestNoDataException;
	
	
	/** 
	 * Create new blog
	 * **/
	BlogBean createNewBlog(BlogBean blogBean) ;
	
	/**Update blog **/
	BlogBean updateBlog(BlogBean blogBean) ;
	
	/**Remove blog **/
	void removeBlog(BlogBean blogBean) ;

}
