package fr.blog.metier;

import org.apache.log4j.Logger;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;

public class Autheur extends _Autheur {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3759669559083018237L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Autheur.class);

	public static Autheur createAutheur(EOEditingContext editingContext) {
		Autheur eo = (Autheur) EOUtilities.createAndInsertInstance(
				editingContext, _Autheur.ENTITY_NAME);
		return eo;
	}

	public String fullName() {
		return this.prenoms() + " " + this.nom();
	}
}
