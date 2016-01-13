package com.epam.training.kk.webapp.menu;

import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import com.epam.training.kk.webapp.app.CustomSession;
import com.epam.training.kk.webapp.component.LanguageComponent;
import com.epam.training.kk.webapp.login.LoginPage;
import com.epam.training.kk.webapp.page.admin.AdminPage;
import com.epam.training.kk.webapp.page.cars.CarsPage;
import com.epam.training.kk.webapp.page.clients.ClientsPage;
import com.epam.training.kk.webapp.page.history.HistoryPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;



public class MenuForAdmin extends MenuForAnonymUser {

	public MenuForAdmin(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new BookmarkablePageLink<Void>("orders", OrdersPage.class));
		add(new BookmarkablePageLink<Void>("clients", ClientsPage.class));
		add(new BookmarkablePageLink<Void>("cars", CarsPage.class));
		add(new BookmarkablePageLink<Void>("history", HistoryPage.class));
		add(new BookmarkablePageLink<Void>("admin", AdminPage.class));
//		BookmarkablePageLink<Void> bpl = new BookmarkablePageLink<Void>("admin", AdminPage.class);
//		 MetaDataRoleAuthorizationStrategy.authorize(bpl, RENDER, "ADMIN");
//		 add(bpl);
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

