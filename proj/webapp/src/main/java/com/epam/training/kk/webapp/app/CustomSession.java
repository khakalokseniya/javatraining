package com.epam.training.kk.webapp.app;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import com.epam.training.kk.services.UserService;

public class CustomSession extends AuthenticatedWebSession {
	@Inject
	private UserService userService;

	private Roles roles;
	private String login;

	public CustomSession(Request request) {
		super(request);
		Injector.get().inject(this);
	}

	public static CustomSession get() {
		return (CustomSession) Session.get();
	}

	@Override
	protected boolean authenticate(String login, String password) {
		if (userService == null) {
			throw new IllegalArgumentException("user service is null");
		}
		try {
			boolean authResult = false;

			authResult = userService.authenticate(login, password);

			if (authResult) {
				this.login = login;
			}
			return authResult;

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return false;
		}
	}

	@Override
	public void signOut() {
		super.signOut();
		login = null;
		roles=null;
	}
	
	

	@Override
	public Roles getRoles() {
		if (roles == null) {
			roles = new Roles();
			if (isSignedIn()) {
				roles.add(Roles.USER);
				if (login.equals("admin")) {
					roles.add(Roles.ADMIN);
				}
			}
		}
		return roles;
	}

}
