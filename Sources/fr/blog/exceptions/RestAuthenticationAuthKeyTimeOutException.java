/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestAuthenticationAuthKeyTimeOutException extends
		SecurityException implements IRestAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3946722643912738764L;
	private static final String ERROR_CODE = "REST-0004";

	public RestAuthenticationAuthKeyTimeOutException(String msg) {
		super(msg);
	}

	public RestAuthenticationAuthKeyTimeOutException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RestAuthenticationAuthKeyTimeOutException() {
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}

}
