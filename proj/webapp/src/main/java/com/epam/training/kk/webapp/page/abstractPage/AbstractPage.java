package com.epam.training.kk.webapp.page.abstractPage;


import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

import com.epam.training.kk.webapp.app.CustomSession;
import com.epam.training.kk.webapp.login.LoginPage;
import com.epam.training.kk.webapp.menu.Menu;
import com.epam.training.kk.webapp.menu.MenuForAdmin;

public class AbstractPage extends WebPage{
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		if (getPageClass().equals(LoginPage.class)) {
			add(new Menu("menu-panel").setVisible(false));
		} else {
			if(CustomSession.get().getRoles().hasRole("ADMIN")){
				add(new MenuForAdmin("menu-panel").setVisible(true));
			}else
			add(new Menu("menu-panel").setVisible(true));
		}
	}
}


