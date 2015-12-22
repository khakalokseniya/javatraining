package com.epam.training.kk.webapp.page.history;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;

public class HistoryPage extends AbstractPage {
	@Inject
	private OrderService orderService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<Order> allOrders = orderService.getAllFromHistory(getAutoIndex(), getAutoIndex());

		add(new ListView<Order>("orders-list", allOrders) {
			@Override
			protected void populateItem(ListItem<Order> item) {
				final Order order = item.getModelObject();
				item.add(new Label("id", order.getId()));
				item.add(new Label("client_id", order.getClientId()));
				item.add(new Label("car_id", order.getCarId()));
				item.add(new Label("address", order.getAddress()));
				item.add(new Label("time", order.getTime()));
				item.add(new Label("distance", order.getDistance()));
				item.add(new Label("price", order.getPrice()));

				item.add(new Link("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new OrdersPage());
					}
				});

			}
		});

		add(new Link("back-link") {
			@Override
			public void onClick() {
				setResponsePage(new OrdersPage());
			}
		});
	}
}
