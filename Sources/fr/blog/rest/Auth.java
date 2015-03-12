/**
 * 
 */
package fr.blog.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.sf.json.JSONObject;
import er.extensions.foundation.ERXStringUtilities;
import fr.blog.exceptions.RestAuthenticationFailureException;
import fr.blog.metier.Blog;
import fr.blog.service.IRestObject;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 f�vr. 2014
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class Auth implements IRestObject {
	public static final String UTILISATEUR_PERSID_PARAMKEY = "utilisateur.persid";

	private static final String CREATIONDATE_KEY = "creationDate";
	private static final String PERSID_KEY = "persId";
	private static final String USER_KEY = "user";

	private String user;
	private Date creationDate;
	private Integer persId;

	/**
	 * 
	 */
	public Auth() {
		// TODO Auto-generated constructor stub
	}

	public Auth(String user, Integer persId) {
		this.user = user;

		this.persId = persId;
		this.creationDate = new Date();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(USER_KEY, getUser());
		map.put(PERSID_KEY, getPersId());
		map.put(CREATIONDATE_KEY, getCreationDate());
		return map;
	}

	public String formatToJson() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}

	public static Auth parseFromJson(String src) {
		JSONObject jsonObject = JSONObject.fromObject(src);
		Auth auth = (Auth) JSONObject.toBean(jsonObject, Auth.class);
		if (auth == null) {
			throw new RestAuthenticationFailureException("Token mal form�.");
		}
		checkCondition(CREATIONDATE_KEY, auth.getCreationDate() != null);
		checkCondition(USER_KEY, !ERXStringUtilities.stringIsNullOrEmpty(auth.getUser()));
		checkCondition(PERSID_KEY, auth.getPersId() != null);
		return auth;
	}

	public static void checkCondition(String attributeName, boolean condition) {
		if (!condition) {
			throw new RestAuthenticationFailureException("Token mal form� : " + attributeName + " est vide");
		}
	}

}
