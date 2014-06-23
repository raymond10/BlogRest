package fr.blog.metier;

import org.apache.log4j.Logger;

import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSTimestamp;

public class Blog extends _Blog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9055440341246850887L;
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Blog.class);
	
	public static Blog createBlog(EOEditingContext editingContext, fr.blog.metier.Autheur autheur) {
			    Blog eo = (Blog) EOUtilities.createAndInsertInstance(editingContext, _Blog.ENTITY_NAME);    
			    eo.setAutheurRelationship(autheur);
			    return eo;
	}
	
	public static Blog createBlog(EOEditingContext editingContext) {
	    Blog eo = (Blog) EOUtilities.createAndInsertInstance(editingContext, _Blog.ENTITY_NAME);    
	    return eo;
   }

	@Override
	public void awakeFromInsertion(EOEditingContext editingContext) {
		super.awakeFromInsertion(editingContext);
		NSTimestamp now = new NSTimestamp();
		setDateCreation(now);
		setDerniereModif(now);
	}
}
