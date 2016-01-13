package com.epam.training.kk.webapp.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;


public class MenuForAnonymUser extends Panel{

	public MenuForAnonymUser(String id) {
		super(id);
	}
	@Override
	protected void onInitialize() {
		super.onInitialize();

	}

}

