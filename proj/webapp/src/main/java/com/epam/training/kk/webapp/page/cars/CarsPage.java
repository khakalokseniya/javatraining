package com.epam.training.kk.webapp.page.cars;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.DriverService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;

public class CarsPage extends AbstractPage {
	
	@Inject
	private CarService carService;
	@Inject
	private DriverService driverService;
	@Inject
	private OrderService orderService;
	
	
	public CarsPage(){}
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Link("newCar-link") {

			@Override
			public void onClick() {
				setResponsePage(new NewCarPage());
			}
		});
		final Form<Object> carsForm = new Form<>("form-cars");
		add(carsForm);
		carsForm.setOutputMarkupId(true);
		CarsDataProvider carsDataProvider = new CarsDataProvider();
		DataView<Car> dataView = new DataView<Car>("cars-list", carsDataProvider, 30) {

			@Override
			protected void populateItem(Item<Car> item) {
				final Car car = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("registrationNumber", car.getRegistrationNumber()));
				item.add(new Label("brand", car.getBrand()));
				item.add(new Label("model", car.getModel()));
				item.add(new Label("type", car.getType()));
				item.add(new Label("color", car.getColor()));
				item.add(new Label("year", car.getYear()));
				item.add(new Label("callsign", car.getCallsign()));
				item.add(new Label("driverId", car.getDriverId()));
				item.add(new Label("fullName", driverService.get(car.getDriverId()).getFullName()));
				item.add(new Label("phoneNumber", driverService.get(car.getDriverId()).getPhoneNumber()));
				item.add(new Label("address", driverService.get(car.getDriverId()).getAddress()));
				item.add(new Label("startingDate", driverService.get(car.getDriverId()).getStartingDate()));
				item.add(new Label("certificate", driverService.get(car.getDriverId()).getCertificate()));
				item.add(new Link("edit-link") {

					@Override
					public void onClick() {
						setResponsePage(new CarEditPage(car));
					}
				});
				
				 AjaxLink alink = new AjaxLink<Client>("delete-link") {
				        @Override
				        public void onClick(AjaxRequestTarget target) {
				        	orderService.findAndDeleteCar(car.getId());
							carService.delete(car.getId());
							target.add(carsForm);
				        }
				     };
				     alink.add(new Label("linklabel", "DELETE"));
				     item.add(alink);

			}
		};
		carsForm.add(dataView);

		carsForm.add(new OrderByBorder<Object>("sortId", "id", carsDataProvider));
		carsForm.add(new OrderByBorder<Object>("sortDriverId", "driverId", carsDataProvider));
		carsForm.add(new PagingNavigator("paging", dataView));
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
			return carService.sort(first, count).iterator();
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


