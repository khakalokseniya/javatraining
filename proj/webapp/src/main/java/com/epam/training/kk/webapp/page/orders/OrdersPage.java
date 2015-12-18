package com.epam.training.kk.webapp.page.orders;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;



public class OrdersPage extends AbstractPage {

	@Inject
	private OrderService orderService;
	private ClientService clientService;

	public OrdersPage() {

	}
	@Override
	protected void onInitialize() {
		super.onInitialize();
		Form<Object> form = new Form<>("form");
		add(form);

		final TextField<String> phoneField = new TextField<String>("phone_number",
				new Model<String>(""));
		form.add(phoneField);
		final TextField<String> addressField = new TextField<String>("address",
				new Model<String>(""));
		form.add(addressField);

	//	final Model<Boolean> isActiveModel = new Model<Boolean>(Boolean.TRUE);
	//	form.add(new CheckBox("active", isActiveModel));

		form.add(new SubmitLink("submit-button") {
			@Override
			public void onSubmit() {
				Client client = new Client(phoneField.getModelObject(), addressField.getModelObject());
				client.setDiscont(5);
				// TODO other fields
				clientService.insert(client);		}
		});

	}
	
}
