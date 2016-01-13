package com.epam.training.kk.webapp.page.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;
import com.epam.training.kk.webapp.page.orders.SetOrdersAtributsPage;

public class HistoryPage extends AbstractPage {
	@Inject
	private OrderService orderService;
	@Inject
	private CarService carService;
	
//	 @Override
//	   protected void onConfigure() {
//	      super.onConfigure();
//	      AuthenticatedWebApplication app = (AuthenticatedWebApplication)Application.get();
//	      if(!AuthenticatedWebSession.get().isSignedIn())
//	         app.restartResponseAtSignInPage();
//	   }
	@Override
	protected void onInitialize() {
		super.onInitialize();
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		add(dataContainer);

		OrdersDataProvider ordersDataProvider = new OrdersDataProvider();
		DataView<Order> dataView = new DataView<Order>("orders-list", ordersDataProvider, 11) {

			@Override
			protected void populateItem(Item<Order> item) {
				final Order order = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("clientPhone", order.getClientPhone()));
				item.add(new Label("carCallsign", carService.get(order.getCarId()).getCallsign()));
				item.add(new Label("address", (Serializable) order.getAddress()));
				item.add(new Label("date", order.getDate()));
				item.add(new Label("time", order.getTime()));
				item.add(new Label("distance", order.getDistance()));
				item.add(new Label("price", order.getPrice()));

				AjaxLink alink = new AjaxLink<Order>("delete-link") {
					@Override
					public void onClick(AjaxRequestTarget target) {
						orderService.delete(order.getId());
						target.add(dataContainer);
					}
				};
				item.add(alink);
			}
		};
		dataContainer.add(dataView);

		AjaxPagingNavigator pager = new AjaxPagingNavigator("paging", dataView) {
			@Override
			protected void onAjaxEvent(AjaxRequestTarget target) {
				target.add(dataContainer);
			}
		};
		dataContainer.add(pager);
		dataContainer.add(new OrderByBorder<Object>("sortId", "id", ordersDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortTime", "time", ordersDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortDate", "date", ordersDataProvider));

	}

	private class OrdersDataProvider extends SortableDataProvider<Order, Object> {

		public OrdersDataProvider() {
			super();
			setSort("date", SortOrder.DESCENDING);
		}

		@Override
		public Iterator<? extends Order> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());

			System.out.println(sort.getProperty() + ":" + currentSort.name());
			return orderService.getCompletedOrders(first, count, sort.isAscending(), (String) sort.getProperty()).iterator();
		}

		@Override
		public long size() {
			return orderService.getCount();
		}

		@Override
		public IModel<Order> model(Order object) {
			return new CompoundPropertyModel<>(object);
		}

	}

}

// List<Order> completedOrders = orderService.getCompletedOrders();
//
// ordersForm.add(new ListView<Order>("orders-list", completedOrders) {
// @Override
// protected void populateItem(ListItem<Order> item) {
// final Order order = item.getModelObject();
// item.add(new Label("id", order.getId()));
// item.add(new Label("client_phone", order.getClientPhone()));
// item.add(new Label("car_id", order.getCarId()));
// item.add(new Label("address", (Serializable) order.getAddress()));
// item.add(new Label("date", order.getDate()));
// item.add(new Label("time", order.getTime()));
// item.add(new Label("distance", order.getDistance()));
// item.add(new Label("price", order.getPrice()));
//
// item.add(new Link("edit-link") {
// @Override
// public void onClick() {
// setResponsePage(new OrdersPage());
// }
// });
//
// AjaxLink alink = new AjaxLink<Client>("delete-link") {
// @Override
// public void onClick(AjaxRequestTarget target) {
// orderService.delete(order.getId());
// target.add(ordersForm);
// }
// };
// alink.add(new Label("linklabel", "DELETE"));
// item.add(alink);
//
// }
// });
//
// ordersForm.add(new Link("back-link") {
// @Override
// public void onClick() {
// setResponsePage(new OrdersPage());
// }
// });
// }
// }
