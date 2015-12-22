package com.epam.training.kk.webapp.page.orders;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.history.HistoryPage;
@AuthorizeInstantiation(value = { "admin" })
public class OrdersPage extends AbstractPage {

	@Inject
	private ClientService clientService;
	@Inject
	private OrderService orderService;
	@Inject
	private CarService carService;

	public OrdersPage() {

	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form<Object> orderForm = new Form<>("form-order");
		add(orderForm);

		

		final TextField<String> phoneField = new TextField<String>("phone_number", new Model<String>(""));
		phoneField.setRequired(true);
		phoneField.add(StringValidator.maximumLength(255));
		orderForm.add(phoneField);

		final TextField<String> addressField = new TextField<String>("address", new Model<String>(""));
		addressField.setRequired(true);
		addressField.add(StringValidator.maximumLength(255));
		orderForm.add(addressField);
		orderForm.setOutputMarkupId(true);
		

		CarsDataProvider carsDataProvider = new CarsDataProvider();
		DataView<Car> dataView = new DataView<Car>("cars-list", carsDataProvider, 10) {
			@Override
			protected void populateItem(Item<Car> item) {
				final Car car = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("callsign", car.getCallsign()));

				item.add(new Link("choose-link") {
					@Override
					public void onClick() {
						setResponsePage(new HistoryPage());
					}
				});
			}
		};
		dataView.add(new OrderByBorder<Object>("sortId", "id", carsDataProvider));
		dataView.add(new OrderByBorder<Object>("sortCallsign", "callsign", carsDataProvider));

		orderForm.add(dataView);
		orderForm.add(new PagingNavigator("paging", dataView));
		orderForm.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				Order order;
				

			}
		});

	}

	private class CarsDataProvider extends SortableDataProvider<Car, Object> {

		public CarsDataProvider() {
			super();
			setSort("id", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends Car> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());

			System.out.println(sort.getProperty() + ":" + currentSort.name());
			// TODO sort in service
			return carService.getAll(first, count).iterator();
		}

		@Override
		public long size() {
			return carService.getCount();
		}

		@Override
		public IModel<Car> model(Car object) {
			return new CompoundPropertyModel<>(object);
		}

	}
}
