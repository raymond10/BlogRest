/**
 * 
 */
package fr.blog.exceptions;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 * 
 */
public interface IRestException {
	public abstract String getErrorCode();

	public abstract String getMessage();

}
