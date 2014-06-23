/**
 * 
 */
package fr.blog.service;

import java.util.List;

import fr.blog.beans.BlogBean;
import fr.blog.exceptions.RestNoDataException;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
public interface BlogService {
	
	/**
	 * Cherche les blogs � partir du token pass� en param�tres
	 * @param token la chaine de recherche
	 * @return les r�sultats
	 */
	List<BlogBean> search(String token);
	
	/**
	 * Cherche un blog en particulier � partir de l'id du blog pass� en param�tres
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
