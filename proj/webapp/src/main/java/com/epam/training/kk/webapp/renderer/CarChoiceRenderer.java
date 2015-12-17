package com.epam.training.kk.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.epam.training.kk.dataaccess.model.Car;

public class CarChoiceRenderer extends ChoiceRenderer<Car> {

	@Override
	public Object getDisplayValue(Car object) {
		return object.getCallsign();
	}

	@Override
	public String getIdValue(Car object, int index) {
		return String.valueOf(object.getId());
	}
}
