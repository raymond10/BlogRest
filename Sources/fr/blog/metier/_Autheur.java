// DO NOT EDIT.  Make changes to Autheur.java instead.
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
public abstract class _Autheur extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Autheur";

  // Attribute Keys
  public static final ERXKey<Integer> AUTHEUR_ID = new ERXKey<Integer>("autheurId");
  public static final ERXKey<String> EMAIL = new ERXKey<String>("email");
  public static final ERXKey<String> NOM = new ERXKey<String>("nom");
  public static final ERXKey<String> PRENOMS = new ERXKey<String>("prenoms");
  // Relationship Keys
  public static final ERXKey<fr.blog.metier.Blog> BLOGS = new ERXKey<fr.blog.metier.Blog>("blogs");

  // Attributes
  public static final String AUTHEUR_ID_KEY = AUTHEUR_ID.key();
  public static final String EMAIL_KEY = EMAIL.key();
  public static final String NOM_KEY = NOM.key();
  public static final String PRENOMS_KEY = PRENOMS.key();
  // Relationships
  public static final String BLOGS_KEY = BLOGS.key();

  private static Logger LOG = Logger.getLogger(_Autheur.class);

  public Autheur localInstanceIn(EOEditingContext editingContext) {
    Autheur localInstance = (Autheur)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public Integer autheurId() {
    return (Integer) storedValueForKey(_Autheur.AUTHEUR_ID_KEY);
  }

  public void setAutheurId(Integer value) {
    if (_Autheur.LOG.isDebugEnabled()) {
    	_Autheur.LOG.debug( "updating autheurId from " + autheurId() + " to " + value);
    }
    takeStoredValueForKey(value, _Autheur.AUTHEUR_ID_KEY);
  }

  public String email() {
    return (String) storedValueForKey(_Autheur.EMAIL_KEY);
  }

  public void setEmail(String value) {
    if (_Autheur.LOG.isDebugEnabled()) {
    	_Autheur.LOG.debug( "updating email from " + email() + " to " + value);
    }
    takeStoredValueForKey(value, _Autheur.EMAIL_KEY);
  }

  public String nom() {
    return (String) storedValueForKey(_Autheur.NOM_KEY);
  }

  public void setNom(String value) {
    if (_Autheur.LOG.isDebugEnabled()) {
    	_Autheur.LOG.debug( "updating nom from " + nom() + " to " + value);
    }
    takeStoredValueForKey(value, _Autheur.NOM_KEY);
  }

  public String prenoms() {
    return (String) storedValueForKey(_Autheur.PRENOMS_KEY);
  }

  public void setPrenoms(String value) {
    if (_Autheur.LOG.isDebugEnabled()) {
    	_Autheur.LOG.debug( "updating prenoms from " + prenoms() + " to " + value);
    }
    takeStoredValueForKey(value, _Autheur.PRENOMS_KEY);
  }

  public NSArray<fr.blog.metier.Blog> blogs() {
    return (NSArray<fr.blog.metier.Blog>)storedValueForKey(_Autheur.BLOGS_KEY);
  }

  public NSArray<fr.blog.metier.Blog> blogs(EOQualifier qualifier) {
    return blogs(qualifier, null, false);
  }

  public NSArray<fr.blog.metier.Blog> blogs(EOQualifier qualifier, boolean fetch) {
    return blogs(qualifier, null, fetch);
  }

  public NSArray<fr.blog.metier.Blog> blogs(EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings, boolean fetch) {
    NSArray<fr.blog.metier.Blog> results;
    if (fetch) {
      EOQualifier fullQualifier;
      EOQualifier inverseQualifier = new EOKeyValueQualifier(fr.blog.metier.Blog.AUTHEUR_KEY, EOQualifier.QualifierOperatorEqual, this);
    	
      if (qualifier == null) {
        fullQualifier = inverseQualifier;
      }
      else {
        NSMutableArray<EOQualifier> qualifiers = new NSMutableArray<EOQualifier>();
        qualifiers.addObject(qualifier);
        qualifiers.addObject(inverseQualifier);
        fullQualifier = new EOAndQualifier(qualifiers);
      }

      results = fr.blog.metier.Blog.fetchBlogs(editingContext(), fullQualifier, sortOrderings);
    }
    else {
      results = blogs();
      if (qualifier != null) {
        results = (NSArray<fr.blog.metier.Blog>)EOQualifier.filteredArrayWithQualifier(results, qualifier);
      }
      if (sortOrderings != null) {
        results = (NSArray<fr.blog.metier.Blog>)EOSortOrdering.sortedArrayUsingKeyOrderArray(results, sortOrderings);
      }
    }
    return results;
  }
  
  public void addToBlogs(fr.blog.metier.Blog object) {
    includeObjectIntoPropertyWithKey(object, _Autheur.BLOGS_KEY);
  }

  public void removeFromBlogs(fr.blog.metier.Blog object) {
    excludeObjectFromPropertyWithKey(object, _Autheur.BLOGS_KEY);
  }

  public void addToBlogsRelationship(fr.blog.metier.Blog object) {
    if (_Autheur.LOG.isDebugEnabled()) {
      _Autheur.LOG.debug("adding " + object + " to blogs relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	addToBlogs(object);
    }
    else {
    	addObjectToBothSidesOfRelationshipWithKey(object, _Autheur.BLOGS_KEY);
    }
  }

  public void removeFromBlogsRelationship(fr.blog.metier.Blog object) {
    if (_Autheur.LOG.isDebugEnabled()) {
      _Autheur.LOG.debug("removing " + object + " from blogs relationship");
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	removeFromBlogs(object);
    }
    else {
    	removeObjectFromBothSidesOfRelationshipWithKey(object, _Autheur.BLOGS_KEY);
    }
  }

  public fr.blog.metier.Blog createBlogsRelationship() {
    EOClassDescription eoClassDesc = EOClassDescription.classDescriptionForEntityName( fr.blog.metier.Blog.ENTITY_NAME );
    EOEnterpriseObject eo = eoClassDesc.createInstanceWithEditingContext(editingContext(), null);
    editingContext().insertObject(eo);
    addObjectToBothSidesOfRelationshipWithKey(eo, _Autheur.BLOGS_KEY);
    return (fr.blog.metier.Blog) eo;
  }

  public void deleteBlogsRelationship(fr.blog.metier.Blog object) {
    removeObjectFromBothSidesOfRelationshipWithKey(object, _Autheur.BLOGS_KEY);
    editingContext().deleteObject(object);
  }

  public void deleteAllBlogsRelationships() {
    Enumeration<fr.blog.metier.Blog> objects = blogs().immutableClone().objectEnumerator();
    while (objects.hasMoreElements()) {
      deleteBlogsRelationship(objects.nextElement());
    }
  }


  public static Autheur createAutheur(EOEditingContext editingContext, Integer autheurId
) {
    Autheur eo = (Autheur) EOUtilities.createAndInsertInstance(editingContext, _Autheur.ENTITY_NAME);    
		eo.setAutheurId(autheurId);
    return eo;
  }

  public static ERXFetchSpecification<Autheur> fetchSpec() {
    return new ERXFetchSpecification<Autheur>(_Autheur.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Autheur> fetchAllAutheurs(EOEditingContext editingContext) {
    return _Autheur.fetchAllAutheurs(editingContext, null);
  }

  public static NSArray<Autheur> fetchAllAutheurs(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Autheur.fetchAutheurs(editingContext, null, sortOrderings);
  }

  public static NSArray<Autheur> fetchAutheurs(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Autheur> fetchSpec = new ERXFetchSpecification<Autheur>(_Autheur.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Autheur> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Autheur fetchAutheur(EOEditingContext editingContext, String keyName, Object value) {
    return _Autheur.fetchAutheur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Autheur fetchAutheur(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Autheur> eoObjects = _Autheur.fetchAutheurs(editingContext, qualifier, null);
    Autheur eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Autheur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Autheur fetchRequiredAutheur(EOEditingContext editingContext, String keyName, Object value) {
    return _Autheur.fetchRequiredAutheur(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Autheur fetchRequiredAutheur(EOEditingContext editingContext, EOQualifier qualifier) {
    Autheur eoObject = _Autheur.fetchAutheur(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Autheur that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Autheur localInstanceIn(EOEditingContext editingContext, Autheur eo) {
    Autheur localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
