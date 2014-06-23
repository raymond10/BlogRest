/**
 * 
 */
package fr.blog.rest.log;

import java.util.List;
import java.util.Map;


import com.webobjects.appserver.WORequest;

import er.extensions.logging.ERXLogger.Factory;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestLoggerFormat extends Factory {

	private static final String SEPARATOR = " | ";

	public static String formatLog(WORequest request, String httpMethod, String route, Map<String, List<Object>> params) {
		StringBuffer sb = new StringBuffer();
		sb.append(httpMethod);
		sb.append(SEPARATOR);
		sb.append(route);
		sb.append(SEPARATOR);
		sb.append(params);
		return sb.toString();
	}

}
