package com.epam.training.kk.webapp.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import com.epam.training.kk.dataaccess.model.Car;

final class CarsTypeChoiceRenderer extends ChoiceRenderer<Car>{
	    
	        
	        public CarsTypeChoiceRenderer()
	        {
	        }

	        
	        @Override
	        public Object getDisplayValue(Car car)
	        {
	            return car.getType();
	        }
	    }

