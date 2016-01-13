package com.epam.training.kk.webapp.page.cars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EnumChoiceRenderer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Car.Type;
import com.epam.training.kk.dataaccess.model.Driver;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.DriverService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class NewCarPage extends AbstractPage {

	@Inject
	private CarService carService;
	@Inject
	private DriverService driverService;

	private Driver driver;
	Long driverId;
	private Car car;

	public NewCarPage() {
		this(new Car(), new Driver());
	}

	public NewCarPage(Car car, Driver driver) {
		super();
		this.car = car;
		this.driver = driver;
	}

	Model<Type> typeModel;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		

		final Form<Driver> drform = new Form<>("create-driver", new CompoundPropertyModel<>(driver));
		add(drform);
		drform.setOutputMarkupId(true);
		
		final FeedbackPanel driverfp = new FeedbackPanel("driver-feedback");
		driverfp.setFilter(new ContainerFeedbackMessageFilter(drform));
		TextField<String> fname = new TextField<String>("fullName");
		fname.setRequired(true);
		drform.add(fname);
		TextField<String> phone = new TextField<String>("phoneNumber");
		phone.setRequired(true);
		drform.add(phone);
		TextField<String> address = new TextField<String>("address");
		address.setRequired(true);
		drform.add(address);
		
		DateTextField dateTextField = new DateTextField("startingDate", new StyleDateConverter("S-", true));
		dateTextField.setRequired(true);
		drform.add(dateTextField);
		DatePicker datePicker = new DatePicker() {
			@Override
			protected String getAdditionalJavaScript() {
				return "${calendar}.cfg.setProperty(\"navigator\",true,false); ${calendar}.render();";
			}
		};
		datePicker.setShowOnFieldClick(true);
		datePicker.setAutoHide(true);
		dateTextField.add(datePicker);
		dateTextField.setRequired(true);
		
		TextField<String> certificate = new TextField<String>("certificate");
		certificate.setRequired(true);
		drform.add(certificate);
		drform.add(new SubmitLink("drsubmit-button") {
			@Override
			public void onSubmit() {
				driverId = driverService.insert(driver);
				driverfp.info("new driver created");
			}
		});
		
		drform.add(driverfp);

		final Form<Car> form = new Form<>("create-car", new CompoundPropertyModel<>(car));
		add(form);
		final FeedbackPanel carfp = new FeedbackPanel("car-feedback");
		carfp.setFilter(new ContainerFeedbackMessageFilter(form));
		TextField<String> registrationNumber = new TextField<String>("registrationNumber");
		registrationNumber.setRequired(true);
		form.add(registrationNumber);
		TextField<String> brand = new TextField<String>("brand");
		brand.setRequired(true);
		form.add(brand);
		TextField<String> model = new TextField<String>("model");
		model.setRequired(true);
		form.add(model);
		
		typeModel = new Model<Type>();
		List<Type> types = Arrays.asList(Type.values());
		DropDownChoice<Type> dropDownChoice = new DropDownChoice<Type>("type", typeModel, types);
		dropDownChoice.setRequired(true);
		form.add(dropDownChoice);
		TextField<String> color = new TextField<String>("color");
		color.setRequired(true);
		form.add(color);
		TextField<Integer> year = new TextField<Integer>("year");
		year.setRequired(true);
		form.add(year);
		TextField<String> callsign = new TextField<String>("callsign");
		callsign.setRequired(true);
		form.add(callsign);
		final Model<Boolean> activity = new Model<Boolean>(Boolean.TRUE);
		form.add(new CheckBox("activity"));

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				if (driverId == null) {
					carfp.info("save the driver");
				} else {
					Type type = typeModel.getObject();
					car.setType(type);
					car.setDriverId(driverId);
					car.setDistance(0);
					carService.insert(car);
					CarsPage pageToResponse = new CarsPage();
					pageToResponse.info("car created!!!!");
					setResponsePage(pageToResponse);
				}
			}
		});
		form.add(carfp);
	}
}
