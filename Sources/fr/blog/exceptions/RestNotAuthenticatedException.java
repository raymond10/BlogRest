/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestNotAuthenticatedException extends SecurityException implements
		IRestAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7561727995977705089L;
	private static final String ERROR_CODE = "REST-0001";

	public RestNotAuthenticatedException(String string) {
		super(string);
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}

}
