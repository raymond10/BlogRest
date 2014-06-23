/**
 * 
 */
package fr.blog;

import com.google.inject.AbstractModule;

import fr.blog.service.AutheurService;
import fr.blog.service.BlogService;
import fr.blog.service.Impl.AutheurServiceImpl;
import fr.blog.service.Impl.BlogServiceImpl;

/**
 * @author Raymond NANEON <raymond.naneon at utt.fr> 26 févr. 2014
 *
 */
public class RestMoldule extends AbstractModule {

	/**
	 * 
	 */
	public RestMoldule() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		bind(BlogService.class).to(BlogServiceImpl.class);
		bind(AutheurService.class).to(AutheurServiceImpl.class);
		//bind(BlogAutheurDetailBean.class).to(BlogAutheurDetailBeanImpl.class);
	}

}
