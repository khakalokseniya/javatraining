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

public class NewCarPage extends AbstractPage{
	
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
		final FeedbackPanel fp = new FeedbackPanel("feedback");
		add(fp);

		final Form<Driver> drform = new Form<>("create-driver", new CompoundPropertyModel<>(driver));
		add(drform);
		drform.setOutputMarkupId(true);
		drform.add(new TextField<String>("fullName"));
		drform.add(new TextField<String>("phoneNumber"));
		drform.add(new TextField<String>("address"));
		
		DateTextField dateTextField = new DateTextField("startingDate",
				new StyleDateConverter("S-", true));
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
		
		drform.add(new TextField<String>("certificate"));
		
		drform.add(new SubmitLink("drsubmit-button") {
			@Override
			public void onSubmit() {
				driverId = driverService.insert(driver);
				fp.info("new driver created");
			}
		});
//		drform.add(new AjaxLink("drsubmit-button") {
//			@Override
//			public void onClick(AjaxRequestTarget target) {
//				driverId = driverService.insert(driver);
//				fp.info("new driver created");
//				target.add(drform);
//			}
//		});
		
		final Form<Car> form = new Form<>("create-car", new CompoundPropertyModel<>(car));
		add(form);

		form.add(new TextField<String>("registrationNumber"));
		form.add(new TextField<String>("brand"));
		form.add(new TextField<String>("model"));
		
		typeModel = new Model<Type>();
		List<Type> types = Arrays.asList(Type.values());
		DropDownChoice<Type> dropDownChoice = new DropDownChoice<Type>("type",
				typeModel,types);
		dropDownChoice.setRequired(true);
		form.add(dropDownChoice);
		
		form.add(new TextField<String>("color"));
		form.add(new TextField<Integer>("year"));
		form.add(new TextField<String>("callsign"));
		final Model<Boolean> activity = new Model<Boolean>(Boolean.TRUE);
		form.add(new CheckBox("activity"));

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				Type type = typeModel.getObject();
				car.setType(type);
				car.setDriverId(driverId);
				carService.insert(car);
				CarsPage pageToResponse = new CarsPage();
				pageToResponse.info("car created!!!!");
				setResponsePage(pageToResponse);
			}
		});
		
		
		
		
	}
}


