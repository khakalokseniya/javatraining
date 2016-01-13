package com.epam.training.kk.webapp.page.admin;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.User;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.UserService;
import com.epam.training.kk.webapp.app.CustomSession;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;

//@AuthorizeInstantiation(value = { "ADMIN" })
public class AdminPage extends AbstractPage {

	@Inject
	private CarService carService;
	@Inject
	private UserService userService;

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new FeedbackPanel("fbp"));
		
		Form<Void> form = new Form<Void>("new-user-form");

		final TextField<String> login = new TextField<String>("login", new Model<String>(null));
		form.add(login);

		final TextField<String> password = new PasswordTextField("password", new Model<String>(null));
		form.add(password);

		add(form);

		form.add(new SubmitLink("submit") {
			@Override
			public void onSubmit() {
				String encodePassword = md5(password.getModelObject());
				User user = new User(login.getModelObject(), encodePassword);
				userService.insert(user);
			}
		});
	}
		private String md5(String md5) {
	        try {
	            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	            byte[] array = md.digest(md5.getBytes());
	            StringBuffer sb = new StringBuffer();
	            for (int i = 0; i < array.length; ++i) {
	                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	            }
	            return sb.toString();
	        } catch (java.security.NoSuchAlgorithmException e) {
	        }
	        return null;
	    }

	}


