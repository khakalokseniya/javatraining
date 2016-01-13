package com.epam.training.kk.webapp.page.clients;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class DetailsPage extends AbstractPage {
	@Inject
	private ClientService clientService;
	@Inject
	private OrderService orderService;
	@Inject
	private CarService carService;

	private Client client;

	public DetailsPage() {
		this(new Client());
	}

	public DetailsPage(Client client) {
		super();
		this.client = client;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		List<Order> allClientsOrders = orderService.getClietsOrders(client);

		add(new ListView<Order>("users-orders", allClientsOrders) {
			@Override
			protected void populateItem(ListItem<Order> item) {
				final Order order = item.getModelObject();
				item.add(new Label("client-phone", order.getClientPhone()));
				item.add(new Label("carCallsign", carService.get(order.getCarId()).getCallsign()));
				item.add(new Label("address", order.getAddress()));
				item.add(new Label("time", order.getTime()));
				item.add(new Label("distance", order.getDistance()));
				item.add(new Label("price", order.getPrice()));
			}
		});
	}
}
