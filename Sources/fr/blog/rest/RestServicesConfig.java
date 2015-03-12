/**
 * 
 */
package fr.blog.rest;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import er.extensions.foundation.ERXProperties;
import fr.blog.Application;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */	
@SuppressWarnings("all")
public class RestServicesConfig {

	private static final String RESTSERVICES_TRANSACTIONLOG_ENABLED = "fr.blog.rest.restServicesconfig.transactionlog.enabled";
	private static final String ER_REST_TIMESTAMP_FORMAT = "er.rest.timestampFormat";
	private static final String ER_REST_TIMESTAMP_DEFAULT_FORMAT = "%d/%m/%Y";

	public static final String RESTSERVICES_TRANSACTIONLOG_FILE_KEY = "fr.blog.rest.restServicesconfig.transactionlog.file";
	public static final String RESTSERVICES_AUTHKEY_TIMEOUT_KEY = "fr.blog.rest.restServicesconfig.authkey.timeout";
	public static final String RESTSERVICES_PAGINATION_PAGE_SIZE_KEY = "fr.blog.rest.restServicesconfig.pagination.pagesize";

	/** Fichier d'enregistrement des logs de transaction */
	private static final String RESTSERVICES_TRANSACTIONS_LOG_FILEPATH = "/tmp/RestServiceTransactions.log";
	/** Durée par défaut (en secondes) avant la péremption d'un token d'authentification (authKey) = 12h */
	private static final int DEFAULT_AUTHKEY_TIMEOUT = 43200;
	/** Nombre par défaut de résultats renvoyés par les requêtes */
	private static final int RESTSERVICES_PAGINATION_PAGE_SIZE_DEFAULT_VALUE = 100;

	private static Logger REST_SERVICES_LOGGER = Logger.getLogger("fr.blog.rest.RestServicesConfig");

	private void initTransactionLogs(String filePath, Logger logger) {
		FileAppender fa = new DailyRollingFileAppender();
		fa.setName("F1");
		fa.setFile(filePath);
		PatternLayout pattern = new PatternLayout();
		String appNameAndPort = getApplication().name() + ":" + getApplication().port();
		pattern.setConversionPattern("%d{dd/MM/yyyy HH:mm:ss} | " + appNameAndPort + " | %-5p| %c %x| %m%n");
		fa.setLayout(pattern);

		fa.setAppend(true);
		fa.activateOptions();

		logger.addAppender(fa);
	}
	
	/**
	 * @return Durée (en secondes) avant la péremption d'un token d'authentification (authKey).
	 * @see FwkCktlRestServices#RESTSERVICES_AUTHKEY_TIMEOUT_KEY
	 */
	public static int getAuthkeyTimeOut() {
		int authkeyTimeOut = ERXProperties.intForKey(RESTSERVICES_AUTHKEY_TIMEOUT_KEY);
		if (authkeyTimeOut == -1) {
			authkeyTimeOut = DEFAULT_AUTHKEY_TIMEOUT;
		}
		return authkeyTimeOut;
	}

	/**
	 * @return Le logger des services REST
	 */
	public static Logger restServicesLogger() {
		return REST_SERVICES_LOGGER;
	}

	public static Application getApplication() {
		return (Application) Application.application();
	}

	/**
	 * @return Le nombre par défaut de résultats renvoyés par les requêtes, sur une page
	 */
	public static int getPaginationDefaultPageSize() {
		int defaultPageSize = ERXProperties.intForKey(RESTSERVICES_PAGINATION_PAGE_SIZE_KEY);
		if (defaultPageSize == -1) {
			defaultPageSize = RESTSERVICES_PAGINATION_PAGE_SIZE_DEFAULT_VALUE;
		}
		return defaultPageSize;
	}

}
