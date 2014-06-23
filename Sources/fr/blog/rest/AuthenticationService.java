/**
 * 
 */
package fr.blog.rest;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import er.extensions.crypting.ERXBlowfishCrypter;
import fr.blog.exceptions.RestAuthenticationAuthKeyTimeOutException;
import fr.blog.exceptions.RestNotAuthenticatedException;
import fr.blog.rest.controllers.RestController;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class AuthenticationService {

	private static final Logger log = Logger.getLogger(AuthenticationService.class);
	private static final ERXBlowfishCrypter CRYPTER = new ERXBlowfishCrypter();

	private IAccessProvider accessProvider = new DefaultAccessProvider();

	/**
	 * A appeler dans tous les services sauf auth.
	 * 
	 * @param authKey La valeur du token
	 * @throws SecurityException Si erreur de cookie ou de droit d'accès
	 */
	public Auth checkAccess(String authKey, String resourceId) throws SecurityException {
		if (authKey != null) {
			String authKeyAsjson = CRYPTER.decrypt(authKey);
			Auth auth = Auth.parseFromJson(authKeyAsjson);
			checkAuth(auth);
			checkUserAccess(auth, resourceId);
			return auth;
		} else {
			log.debug("checkAuthKey : token d'authentification " + RestController.AUTH_KEY + " non trouvé : " + authKey);
			throw new RestNotAuthenticatedException("Token d'authentification non trouvé");
		}
	}

	/**
	 * Verifie la validite de la authKey.
	 * 
	 * @param auth Un object {@link Auth}
	 * @throws SecurityException Si erreur
	 */
	private void checkAuth(Auth auth) throws SecurityException {
		DateTime now = new DateTime();
		DateTime authDate = new DateTime(auth.getCreationDate());

		if (now.isAfter(authDate.plusSeconds(RestServicesConfig.getAuthkeyTimeOut()))) {
			throw new RestAuthenticationAuthKeyTimeOutException("Token d'authentification " + RestController.AUTH_KEY + " périmé.");
		}
	}

	protected void checkUserAccess(Auth auth, String resourceId) {
		getAccessProvider().checkAccess(auth, resourceId);
	}

	/**
	 * @param user Le login de l'utilisateur
	 * @return Le login crypté
	 */
	public String encryptUser(String user) {
		return CRYPTER.encrypt(user);
	}

	public IAccessProvider getAccessProvider() {
		return accessProvider;
	}

	public void setAccessProvider(IAccessProvider accessProvider) {
		this.accessProvider = accessProvider;
	}

}
