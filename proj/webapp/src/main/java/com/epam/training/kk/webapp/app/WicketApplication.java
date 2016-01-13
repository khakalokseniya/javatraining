package com.epam.training.kk.webapp.app;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Timer;

import org.apache.wicket.Application;
import org.apache.wicket.application.ComponentInstantiationListenerCollection;
import org.apache.wicket.authorization.strategies.page.SimplePageAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.epam.training.kk.webapp.login.AuthenticatedPage;
import com.epam.training.kk.webapp.login.LoginPage;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.admin.AdminPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;
import com.epam.training.kk.webapp.tasks.DriverTask;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see com.epam.training.webapp.StartJetty#main(String[])
 */
@Component("MyWebApplication")
public class WicketApplication extends AuthenticatedWebApplication {

	@Autowired
	private ApplicationContext context;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return OrdersPage.class;
	}
	
	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		ComponentInstantiationListenerCollection componentInstantiationListeners = getComponentInstantiationListeners();
		componentInstantiationListeners.add(new SpringComponentInjector(this, context));

		getMarkupSettings().setStripWicketTags(true);
		  getSecuritySettings().setAuthorizationStrategy(new MetaDataRoleAuthorizationStrategy(this));
	   MetaDataRoleAuthorizationStrategy.authorize(AdminPage.class, Roles.ADMIN);
	      
	   
	      //запустить task
	     Timer timer = new Timer(); 
	     timer.schedule(new DriverTask(), 3000);
	}
	   
	
	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return AuthenticatedPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return CustomSession.class;
	}
}
