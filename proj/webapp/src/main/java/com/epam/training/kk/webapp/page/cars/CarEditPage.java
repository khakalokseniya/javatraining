package com.epam.training.kk.webapp.page.cars;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Car.Type;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class CarEditPage extends AbstractPage {
	@Inject
	private CarService carService;

	private Car car;

	public CarEditPage() {
		this(new Car());
	}

	public CarEditPage(Car car) {
		super();
		this.car = car;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
  //PropertyModel
		add(new FeedbackPanel("feedback"));

		final Form<Car> form = new Form<>("form-car", new CompoundPropertyModel<>(car));
		add(form);

		form.add(new TextField<String>("registrationNumber"));
		form.add(new TextField<String>("brand"));
		form.add(new TextField<String>("model"));
		form.add(new TextField<Type>("type"));
		form.add(new TextField<String>("color"));
		form.add(new TextField<Integer>("year"));
		form.add(new TextField<String>("callsign"));
		form.add(new TextField<Long>("driverId"));
		final Model<Boolean> activity = new Model<Boolean>(Boolean.TRUE);
		form.add(new CheckBox("activity"));

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				
			
				carService.update(car.getRegistrationNumber(), car.getBrand(), car.getModel(), car.getType(), car.getColor(), car.getYear(), car.getCallsign(), car.getDriverId(), car.getActivity(),
						car.getId());
				CarsPage pageToResponse = new CarsPage();
				pageToResponse.info("car updated!!!!");
				setResponsePage(pageToResponse);
			}
		});

	}
}
