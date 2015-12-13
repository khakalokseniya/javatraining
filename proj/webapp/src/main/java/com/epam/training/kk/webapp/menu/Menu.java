package com.epam.training.kk.webapp.menu;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.epam.training.kk.webapp.page.cars.CarsPage;
import com.epam.training.kk.webapp.page.clients.ClientsPage;
import com.epam.training.kk.webapp.page.history.HistoryPage;
import com.epam.training.kk.webapp.page.map.MapPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;

public class Menu extends Panel {

	public Menu(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new BookmarkablePageLink<Void>("orders", OrdersPage.class));
		add(new BookmarkablePageLink<Void>("clients", ClientsPage.class));
		add(new BookmarkablePageLink<Void>("map", MapPage.class));
		add(new BookmarkablePageLink<Void>("cars", CarsPage.class));
		add(new BookmarkablePageLink<Void>("history", HistoryPage.class));
	}
}
