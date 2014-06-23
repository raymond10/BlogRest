/**
 * 
 */
package fr.blog.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXEC;
import er.extensions.localization.ERXLocalizer;
import fr.blog.beans.AutheurBean;
import fr.blog.beans.Impl.AutheurBeanImpl;
import fr.blog.exceptions.RestNoDataException;
import fr.blog.metier.Autheur;
import fr.blog.metier.Blog;
import fr.blog.service.AutheurService;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 28 févr. 2014
 *
 */
@SuppressWarnings("all")
public class AutheurServiceImpl implements AutheurService {

	private EOEditingContext edc = ERXEC.newEditingContext();

	/* (non-Javadoc)
	 * @see fr.blog.service.AutheurService#search(java.lang.String)
	 */
	@Override
	public List<AutheurBean> search(String token) throws RestNoDataException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<AutheurBean> beans = new ArrayList<AutheurBean>();
		NSArray<Autheur> autheurs = Autheur.fetchAutheurs(edc, Autheur.NOM.contains(token), Autheur.NOM.ascs());
		for (Autheur autheur : autheurs) {
			AutheurBean bean = new AutheurBeanImpl();
			bean.setAutheurId(autheur.autheurId());
			bean.setNom(autheur.nom());
			bean.setPrenom(autheur.prenoms());
			bean.setEmail(autheur.email());
			bean.setBlogs(getBlogsPourAutheur(autheur));
			beans.add(bean);
		}
		return beans;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.AutheurService#autheurById(java.lang.Integer)
	 */
	@Override
	public AutheurBean autheurById(Integer autheurId)
			throws RestNoDataException {
		// TODO Auto-generated method stub
		Autheur autheur = Autheur.fetchAutheur(edc,
				Autheur.AUTHEUR_ID.eq(autheurId));
		AutheurBean bean;
		if (autheur == null) {
			throw new RestNoDataException(ERXLocalizer.defaultLocalizer()
					.localizedStringForKey("autheur.nontrouve"));
		} else {
			bean = new AutheurBeanImpl();
			bean.setAutheurId(autheurId);
			bean.setNom(autheur.nom());
			bean.setPrenom(autheur.prenoms());
			bean.setEmail(autheur.email());
			bean.setBlogs(getBlogsPourAutheur(autheur));
		}
		return bean;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.AutheurService#createNewAutheur(fr.blog.beans.AutheurBean)
	 */
	@Override
	public AutheurBean createNewAutheur(AutheurBean autheurBean) {
		// TODO Auto-generated method stub
		EOEditingContext myCreateEdc = ERXEC.newEditingContext();
		Autheur autheur = Autheur.createAutheur(myCreateEdc);
		autheur.setNom(autheurBean.getNom());
		autheur.setPrenoms(autheurBean.getPrenom());
		autheur.setEmail(autheurBean.getEmail());
		autheur.editingContext().saveChanges();
		autheurBean.setAutheurId(autheur.autheurId());
		return autheurBean;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.AutheurService#updateAutheur(fr.blog.beans.AutheurBean)
	 */
	@Override
	public AutheurBean updateAutheur(AutheurBean autheurBean) {
		// TODO Auto-generated method stub
		EOEditingContext myUpdateEdc = ERXEC.newEditingContext();
		Autheur autheur = Autheur.fetchAutheur(myUpdateEdc, Autheur.AUTHEUR_ID.eq(autheurBean.getAutheurId()));
		autheur.setNom(autheurBean.getNom());
		autheur.setPrenoms(autheurBean.getPrenom());
		autheur.setEmail(autheurBean.getEmail());
		autheur.editingContext().saveChanges();
		return autheurBean;
	}

	/* (non-Javadoc)
	 * @see fr.blog.service.AutheurService#removeAutheur(fr.blog.beans.AutheurBean)
	 */
	@Override
	public void removeAutheur(AutheurBean autheurBean) {
		// TODO Auto-generated method stub
		EOEditingContext myRemoveEdc = ERXEC.newEditingContext();
		Autheur autheur = Autheur.fetchAutheur(myRemoveEdc, Autheur.AUTHEUR_ID.eq(autheurBean.getAutheurId()));
		myRemoveEdc.deleteObject(autheur);
		myRemoveEdc.saveChanges();
	}
	
	protected List<String> getBlogsPourAutheur(Autheur autheur) {
		return (List<String>) autheur.blogs().valueForKey(Blog.TITRE_KEY);
	}

}
