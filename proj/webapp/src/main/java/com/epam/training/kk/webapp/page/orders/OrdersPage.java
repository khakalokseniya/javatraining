package com.epam.training.kk.webapp.page.orders;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.AbstractChoice.LabelPosition;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.renderer.CarChoiceRenderer;

public class OrdersPage extends AbstractPage {

	@Inject
	private ClientService clientService;
	@Inject
	private OrderService orderService;
	@Inject
	private CarService carService;
	private Timer timer;
	private Order order;
	private List<Order> currentList;
	private IModel<Car> carModel;

	public OrdersPage() {
		this.order = new Order();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		

		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer") ;

		dataContainer.setOutputMarkupId(true);
		add(dataContainer);

		OrdersDataProvider ordersDataProvider = new OrdersDataProvider();
		DataView<Order> dataView = new DataView<Order>("orders-list", ordersDataProvider, 9) {

			@Override
			protected void populateItem(Item<Order> item) {
				final Order order = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("client-phone", order.getClientPhone()));
				item.add(new Label("carCallsign", carService.get(order.getCarId()).getCallsign()));
				item.add(new Label("address", order.getAddress()));
				item.add(new Label("time", order.getTime()));
				item.add(new Label("distance", order.getDistance()));
				item.add(new Label("price", order.getPrice()));
				item.add(new Label("isCompleted", order.isCompleted()));

				item.add(new Link("update-link") {

					@Override
					public void onClick() {
						setResponsePage(new SetOrdersAtributsPage(order));
					}
				});
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
		dataContainer.add(new OrderByBorder<Object>("sortTime", "time", ordersDataProvider));

		final Form<Order> orderForm = new Form<>("form-order", new CompoundPropertyModel<>(order));
		add(orderForm);
		orderForm.setOutputMarkupId(true);

		final TextField<Integer> phoneField = new TextField<Integer>("clientPhone");
		phoneField.setRequired(true);
		phoneField.add(StringValidator.maximumLength(255));
		orderForm.add(phoneField);

		final TextField<String> streetField = new TextField<String>("address.street");
		streetField.setRequired(true);
		orderForm.add(streetField);
		final TextField<String> houseField = new TextField<String>("address.house");
		houseField.setRequired(true);
		orderForm.add(houseField);
		final TextField<String> corpsField = new TextField<String>("address.corps");
		orderForm.add(corpsField);
		final TextField<String> apartmentField = new TextField<String>("address.apartment");
		orderForm.add(apartmentField);

		orderForm.setOutputMarkupId(true);

		List<Car> cars = carService.getAll();
		carModel = new Model<Car>();
		RadioChoice<Car> carChoice = new RadioChoice<>("car-choice", carModel, cars, new CarChoiceRenderer());
		carChoice.setRequired(true);
		carChoice.setLabelPosition(LabelPosition.WRAP_BEFORE);
		orderForm.add(carChoice);
		final FeedbackPanel fbpanel = new FeedbackPanel("feedback");
		orderForm.add(fbpanel);
		orderForm.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {

				Car car = (Car) carModel.getObject();
				String phone = "+375" + phoneField.getInput();
				order.setClientPhone(phone);
				order.setCarId(car.getId());
				orderService.setDepartureTime(order);
				orderService.setDate(order);
				clientService.findByPhone(order.getClientPhone());
				orderService.insert(order);
				if (!phoneField.getInput().matches("[0-9]+")) {
					fbpanel.info("phone field allows only numbers");
				} else
					setResponsePage(new OrdersPage());

			}
		});

	}

	private class OrdersDataProvider extends SortableDataProvider<Order, Object> {

		public OrdersDataProvider() {
			super();
			setSort("time", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends Order> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());
			System.out.println(sort.getProperty() + ":" + currentSort.name());
			return orderService.getCurrentOrders(first, count, sort.isAscending(), (String) sort.getProperty()).iterator();
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
