/**
 * 
 */
package fr.blog.beans;

import java.util.List;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public interface AutheurBean {
	
	Integer getAutheurId();
	void setAutheurId(Integer Id);
	
	String getNom();	
	void setNom(String nom);
	
	String getPrenom();
	void setPrenom(String prenom);
	
	String getEmail();
	void setEmail(String email);
	
	List<String> getBlogs();
	void setBlogs(List<String> blogs);
}
