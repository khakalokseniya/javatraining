package com.epam.training.kk.webapp.page.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.thread.Task;

import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.dataaccess.model.User;
import com.epam.training.kk.services.CarService;
import com.epam.training.kk.services.OrderService;
import com.epam.training.kk.services.UserService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.orders.OrdersPage;
import com.epam.training.kk.webapp.page.orders.TimerManager;

//@AuthorizeInstantiation(value = { "ADMIN" })
public class AdminPage extends AbstractPage {

	@Inject
	private CarService carService;
	@Inject
	private UserService userService;
	@Inject
	private OrderService orderService;

	private TimerManager timer;

	public AdminPage() {
		this.timer = TimerManager.getInstance();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		final FeedbackPanel fbp = new FeedbackPanel("fbp");
		fbp.setOutputMarkupId(true);
		add(fbp);

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

		add(new AjaxLink("start-task") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				timer.start(new TimerTask() {
					@Override
					public void run() {
						updateOrder(getOrders());
						
					}
				}, new Timer());
			
				
				fbp.info("thread is started");
				target.add(fbp);
			}
		});

		add(new Link("stop-task") {
			@Override
			public void onClick() {
				timer.stop();
				
				fbp.info("thread is stopped");
			}
		});

	}

	private String md5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}

	private List<Order> getOrders() {// TODO in services
		List<Order> all = orderService.getAll();
		List<Order> orders = new ArrayList();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).isCompleted() == false) {
				orders.add(all.get(i));
			}
		}
		return orders;

	}

	private void updateOrder(List<Order> orders) {
		Random randomizer = new Random();
		Order random = orders.get(randomizer.nextInt(orders.size()));
		int distance = randomizer.nextInt(10 - 1 + 1) + 1;
		int price = distance * 5000;
		orderService.updateDriverInfo(distance, price, true, random.getId());
	}

}
