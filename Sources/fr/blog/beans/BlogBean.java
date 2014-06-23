/**
 * 
 */
package fr.blog.beans;

import com.webobjects.foundation.NSTimestamp;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public interface BlogBean {
	
	Integer getBlogId();
	void setBlogId(Integer Id);
	
	Integer getAutheurId();
	void setAutheurId(Integer Id);
	
	String getTitre();
	void setTitre(String titre);
	
	String getContenu();
	void setContenu(String contenu);
	
	NSTimestamp getDateCreation();
	void setDateCreation(NSTimestamp dateCreation);
	
	NSTimestamp getDerniereModif();
	void setDerniereModif(NSTimestamp derniereModif);
	
	String getAutheur();
	void setAutheur(String autheur);

}
