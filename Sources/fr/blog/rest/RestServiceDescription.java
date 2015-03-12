/**
 * 
 */
package fr.blog.rest;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class RestServiceDescription implements Comparable<RestServiceDescription> {
	private String strId;
	private String libelle;
	private String categorie;
	private String commentaireDeveloppeur;
	private String commentaireUtilisateur;
	private Collection<? extends IRestParamBean> params;
	private String route;
	private String countRoute;
	
	public RestServiceDescription() {
		
	}
	
	/** {@inheritDoc} */
	public int compareTo(RestServiceDescription o) {
		return getLibelle().compareTo(o.getLibelle());
	}

}
