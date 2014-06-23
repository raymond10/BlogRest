/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestParseException extends Exception implements IRestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8861108809933799932L;
	private static final String ERROR_CODE = "REST-0006";

	public RestParseException() {
	}

	public RestParseException(String msg) {
		super(msg);
	}

	public RestParseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public String getErrorCode() {
		return ERROR_CODE;
	}

}
