/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestNoDataException extends Exception implements IRestException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6366980332252963791L;
	private static final String ERROR_CODE = "REST-0005";

	/**
	 * @param arg0
	 */
	public RestNoDataException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see fr.blog.exceptions.IRestException#getErrorCode()
	 */
	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return ERROR_CODE;
	}

}
