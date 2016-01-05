package com.epam.training.kk.webapp.page.orders;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.AbstractChoice.LabelPosition;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.util.convert.ConversionException;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.validator.StringValidator;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.renderer.CarChoiceRenderer;

@AuthorizeInstantiation(value = { "admin" })
public class OrdersPage extends AbstractPage {

	@Inject
	private ClientService clientService;
	@Inject
	private OrderService orderService;
	@Inject
	private CarService carService;

	private Order order;

	private IModel<Car> carModel;

	public OrdersPage() {
		this.order = new Order();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Form<Order> orderForm = new Form<>("form-order", new CompoundPropertyModel<>(order));
		add(orderForm);

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

		carChoice.setLabelPosition(LabelPosition.WRAP_BEFORE);
		orderForm.add(carChoice);

		orderForm.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit()  {
				Car car = (Car) carModel.getObject();
				order.setCarId(car.getId());
				orderService.setDepartureTime(order);
				clientService.findByPhone(order.getClientPhone());
				orderService.insert(order);
				setResponsePage(new OrdersPage());

			}
		});

	final Form<Object> currentOrdersForm = new Form<>("form-currentOrders");
		add(currentOrdersForm);
		orderForm.setOutputMarkupId(true);
		OrdersDataProvider ordersDataProvider = new OrdersDataProvider();
		DataView<Order> dataView = new DataView<Order>("orders-list", ordersDataProvider, 10) {

			@Override
			protected void populateItem(Item<Order> item) {
				final Order order = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("clientPhone", order.getClientPhone()));
				item.add(new Label("carCallsign", carService.get(order.getCarId()).getCallsign()));
				item.add(new Label("address", order.getAddress()));
				item.add(new Label("time", order.getTime()));
				item.add(new Label("distance", order.getDistance()));
				item.add(new Label("price", order.getPrice()));
				item.add(new Label("isCompleted", order.isCompleted()));

				item.add(new AjaxLink("update-link") {

					@Override
					public void onClick(AjaxRequestTarget target) {
						target.add(currentOrdersForm);
						Order order = (Order) currentOrdersForm.getModel().getObject();
						if(carService.get(order.getCarId()).getActivity()==true){
							
						}
					}
				});

			}
		};
		currentOrdersForm.add(dataView);

		currentOrdersForm.add(new OrderByBorder<Object>("sortId", "id", ordersDataProvider));
		currentOrdersForm.add(new OrderByBorder<Object>("sortTime", "time", ordersDataProvider));
		
		currentOrdersForm.add(new AjaxLink("ordersUpdate-button") {
			@Override
			public void onClick(AjaxRequestTarget target)  {
				
				target.add(currentOrdersForm);

			}
		});
	}
	private static final JavaScriptResourceReference MYPAGE_JS = new JavaScriptResourceReference(OrdersPage.class, "base.js");
	@Override
	public void renderHead(IHeaderResponse response) {
	  response.render(JavaScriptReferenceHeaderItem.forReference(MYPAGE_JS));
	}

	private class OrdersDataProvider extends SortableDataProvider<Order, Object> {

		public OrdersDataProvider() {
			super();
			setSort("id", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends Order> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());

			System.out.println(sort.getProperty() + ":" + currentSort.name());
			return orderService.sort(first, count).iterator();
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
