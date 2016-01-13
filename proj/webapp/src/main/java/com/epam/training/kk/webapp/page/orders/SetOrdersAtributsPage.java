package com.epam.training.kk.webapp.page.orders;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.Car;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.cars.CarsPage;



public class SetOrdersAtributsPage extends AbstractPage{
	@Inject
	private OrderService orderService;
	@Inject
	private ClientService clientService;
	
	private Order order;

	public SetOrdersAtributsPage() {
		this(new Order());
	}

	public SetOrdersAtributsPage(Order order) {
		super();
		this.order = order;
	}
	
	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new FeedbackPanel("feedback"));
		
		final Form<Order> form = new Form<>("form-ordersAtributs", new CompoundPropertyModel<>(order));
		add(form);
		
		final TextField<Double> distanceField = new TextField<Double>("distance");
		distanceField.setRequired(true);
		form.add(distanceField);
		final Model<Boolean> isCompleted = new Model<Boolean>(Boolean.TRUE);
		form.add(new CheckBox("isCompleted"));
		final Model<Boolean> useDiscont = new Model<Boolean>(Boolean.TRUE);
		form.add(new CheckBox("useDiscont"));
		
		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				double price;
				if(useDiscont.getObject()==true){
					price = distanceField.getModelObject()*order.getPricePerKm()-clientService.get(clientService.findByPhone(order.getClientPhone())).getDiscont(); 
				}else{
				price = distanceField.getModelObject()*order.getPricePerKm();
				}
				order.setPrice(price);
				orderService.updateDriverInfo(order.getDistance(), price, isCompleted.getObject(), order.getId());
				orderService.updateClient(order, useDiscont.getObject());
				OrdersPage pageToResponse = new OrdersPage();
				pageToResponse.info("order updated!!!!");
				setResponsePage(pageToResponse);
			}
		});

		
	}
}
	
	
