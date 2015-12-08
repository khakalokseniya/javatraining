package com.epam.training.kk.webapp.app;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.training.kk.webapp.page.HomePage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.epam.training.webapp.StartJetty#main(String[])
 */
@Component("MyWebApplication")
public class WicketApplication extends WebApplication {

	@Autowired
	private ApplicationContext context;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this, context));

		// add your configuration here
	}
}
