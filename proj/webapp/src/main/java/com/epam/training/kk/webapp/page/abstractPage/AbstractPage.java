package com.epam.training.kk.webapp.page.abstractPage;


import org.apache.wicket.markup.html.WebPage;

import com.epam.training.kk.webapp.menu.Menu;
import com.epam.training.kk.webapp.page.orders.OrdersPage;

public class AbstractPage extends WebPage{
	
	@Override
	protected void onInitialize() {

		super.onInitialize();

		if (getPageClass().equals(OrdersPage.class)) {
			add(new Menu("menu-panel"));
		} else {
			add(new Menu("menu-panel"));
		}

	}

	
}

