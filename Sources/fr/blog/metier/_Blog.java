// DO NOT EDIT.  Make changes to Blog.java instead.
package fr.blog.metier;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _Blog extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Blog";

  // Attribute Keys
  public static final ERXKey<Integer> AUTHEUR_ID = new ERXKey<Integer>("autheurId");
  public static final ERXKey<Integer> BLOG_ID = new ERXKey<Integer>("blogId");
  public static final ERXKey<String> CONTENU = new ERXKey<String>("contenu");
  public static final ERXKey<NSTimestamp> DATE_CREATION = new ERXKey<NSTimestamp>("dateCreation");
  public static final ERXKey<NSTimestamp> DERNIERE_MODIF = new ERXKey<NSTimestamp>("derniereModif");
  public static final ERXKey<String> TITRE = new ERXKey<String>("titre");
  // Relationship Keys
  public static final ERXKey<fr.blog.metier.Autheur> AUTHEUR = new ERXKey<fr.blog.metier.Autheur>("autheur");

  // Attributes
  public static final String AUTHEUR_ID_KEY = AUTHEUR_ID.key();
  public static final String BLOG_ID_KEY = BLOG_ID.key();
  public static final String CONTENU_KEY = CONTENU.key();
  public static final String DATE_CREATION_KEY = DATE_CREATION.key();
  public static final String DERNIERE_MODIF_KEY = DERNIERE_MODIF.key();
  public static final String TITRE_KEY = TITRE.key();
  // Relationships
  public static final String AUTHEUR_KEY = AUTHEUR.key();

  private static Logger LOG = Logger.getLogger(_Blog.class);

  public Blog localInstanceIn(EOEditingContext editingContext) {
    Blog localInstance = (Blog)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Integer autheurId() {
    return (Integer) storedValueForKey(_Blog.AUTHEUR_ID_KEY);
  }

  public void setAutheurId(Integer value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating autheurId from " + autheurId() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.AUTHEUR_ID_KEY);
  }

  public Integer blogId() {
    return (Integer) storedValueForKey(_Blog.BLOG_ID_KEY);
  }

  public void setBlogId(Integer value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating blogId from " + blogId() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.BLOG_ID_KEY);
  }

  public String contenu() {
    return (String) storedValueForKey(_Blog.CONTENU_KEY);
  }

  public void setContenu(String value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating contenu from " + contenu() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.CONTENU_KEY);
  }

  public NSTimestamp dateCreation() {
    return (NSTimestamp) storedValueForKey(_Blog.DATE_CREATION_KEY);
  }

  public void setDateCreation(NSTimestamp value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating dateCreation from " + dateCreation() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.DATE_CREATION_KEY);
  }

  public NSTimestamp derniereModif() {
    return (NSTimestamp) storedValueForKey(_Blog.DERNIERE_MODIF_KEY);
  }

  public void setDerniereModif(NSTimestamp value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating derniereModif from " + derniereModif() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.DERNIERE_MODIF_KEY);
  }

  public String titre() {
    return (String) storedValueForKey(_Blog.TITRE_KEY);
  }

  public void setTitre(String value) {
    if (_Blog.LOG.isDebugEnabled()) {
    	_Blog.LOG.debug( "updating titre from " + titre() + " to " + value);
    }
    takeStoredValueForKey(value, _Blog.TITRE_KEY);
  }

  public fr.blog.metier.Autheur autheur() {
    return (fr.blog.metier.Autheur)storedValueForKey(_Blog.AUTHEUR_KEY);
  }
  
  public void setAutheur(fr.blog.metier.Autheur value) {
    takeStoredValueForKey(value, _Blog.AUTHEUR_KEY);
  }

  public void setAutheurRelationship(fr.blog.metier.Autheur value) {
    if (_Blog.LOG.isDebugEnabled()) {
      _Blog.LOG.debug("updating autheur from " + autheur() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setAutheur(value);
    }
    else if (value == null) {
    	fr.blog.metier.Autheur oldValue = autheur();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Blog.AUTHEUR_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Blog.AUTHEUR_KEY);
    }
  }
  

  public static Blog createBlog(EOEditingContext editingContext, Integer autheurId
, Integer blogId
, fr.blog.metier.Autheur autheur) {
    Blog eo = (Blog) EOUtilities.createAndInsertInstance(editingContext, _Blog.ENTITY_NAME);    
		eo.setAutheurId(autheurId);
		eo.setBlogId(blogId);
    eo.setAutheurRelationship(autheur);
    return eo;
  }

  public static ERXFetchSpecification<Blog> fetchSpec() {
    return new ERXFetchSpecification<Blog>(_Blog.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Blog> fetchAllBlogs(EOEditingContext editingContext) {
    return _Blog.fetchAllBlogs(editingContext, null);
  }

  public static NSArray<Blog> fetchAllBlogs(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Blog.fetchBlogs(editingContext, null, sortOrderings);
  }

  public static NSArray<Blog> fetchBlogs(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Blog> fetchSpec = new ERXFetchSpecification<Blog>(_Blog.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Blog> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Blog fetchBlog(EOEditingContext editingContext, String keyName, Object value) {
    return _Blog.fetchBlog(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Blog fetchBlog(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Blog> eoObjects = _Blog.fetchBlogs(editingContext, qualifier, null);
    Blog eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Blog that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Blog fetchRequiredBlog(EOEditingContext editingContext, String keyName, Object value) {
    return _Blog.fetchRequiredBlog(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Blog fetchRequiredBlog(EOEditingContext editingContext, EOQualifier qualifier) {
    Blog eoObject = _Blog.fetchBlog(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Blog that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Blog localInstanceIn(EOEditingContext editingContext, Blog eo) {
    Blog localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
