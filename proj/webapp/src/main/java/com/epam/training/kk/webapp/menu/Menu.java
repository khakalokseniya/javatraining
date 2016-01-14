package com.epam.training.kk.webapp.menu;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.epam.training.kk.webapp.app.CustomSession;
import com.epam.training.kk.webapp.component.LanguageComponent;
import com.epam.training.kk.webapp.login.LoginPage;
import com.epam.training.kk.webapp.page.cars.CarsPage;
import com.epam.training.kk.webapp.page.clients.ClientsPage;
import com.epam.training.kk.webapp.page.history.HistoryPage;
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
		add(new BookmarkablePageLink<Void>("cars", CarsPage.class));
		add(new BookmarkablePageLink<Void>("history", HistoryPage.class));
		add(new Link("logout") {

			@Override
			public void onClick() {
				CustomSession.get().signOut();
				setResponsePage(LoginPage.class);
			}

		});

		add(new LanguageComponent("lang-select"));

	}
}
