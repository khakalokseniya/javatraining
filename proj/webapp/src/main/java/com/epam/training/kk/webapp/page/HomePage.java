package com.epam.training.kk.webapp.page;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;

import com.epam.training.kk.services.OrderService;

public class HomePage extends AbstractPage {

	@Inject
	private OrderService orderService;

	public HomePage() {

		add(new Link<Void>("home") {

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});
		add(new Link<Void>("new client") {

			@Override
			public void onClick() {
				setResponsePage(NewClientPage.class);
			}
		});
	}
}
