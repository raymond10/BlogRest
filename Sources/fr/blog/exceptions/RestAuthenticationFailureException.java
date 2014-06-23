/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestAuthenticationFailureException extends SecurityException
		implements IRestAuthException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_CODE = "CKTLREST-0002";

	public RestAuthenticationFailureException(String msg) {
		super(msg);
	}

	public RestAuthenticationFailureException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public RestAuthenticationFailureException() {
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}

}
