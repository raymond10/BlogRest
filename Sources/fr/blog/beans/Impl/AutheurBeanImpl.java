/**
 * 
 */
package fr.blog.beans.Impl;

import java.util.List;

import fr.blog.beans.AutheurBean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class AutheurBeanImpl implements AutheurBean {
	
	private Integer autheurId;
	private String nom;
	private String prenom;
	private String email;
	private List<String> blogs;

	/**
	 * 
	 */
	public AutheurBeanImpl() {
		// TODO Auto-generated constructor stub
	}
}
