package com.epam.training.kk.webapp.login;

import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class AuthenticatedPage extends AbstractPage {
	   @Override
	   protected void onConfigure() {
	      super.onConfigure();
	      AuthenticatedWebApplication app = (AuthenticatedWebApplication)Application.get();
	      if(!AuthenticatedWebSession.get().isSignedIn())
	         app.restartResponseAtSignInPage();
	   }
	   @Override
	   protected void onInitialize() {
	      super.onInitialize();
	      add(new Link("goToHomePage") {

	         @Override
	         public void onClick() {
	            setResponsePage(getApplication().getHomePage());
	         }
	      });

	      add(new Link("logOut") {

	         @Override
	         public void onClick() {
	            AuthenticatedWebSession.get().invalidate();
	            setResponsePage(getApplication().getHomePage());
	         }
	      });
	   }
}


