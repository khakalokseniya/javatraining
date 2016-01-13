package com.epam.training.kk.webapp.component;

import java.util.Locale;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;


public class LanguageComponent extends Panel {

	public LanguageComponent(String id) {
		super(id);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new LangSelectionLink("ru"));
		add(new LangSelectionLink("en"));
	}

	private final class LangSelectionLink extends Link<Void> {
		private LangSelectionLink(String id) {
			super(id);
		}

		@Override
		protected void onConfigure() {
			super.onConfigure();
			Locale locale = Session.get().getLocale();
			String lang = locale == null ? null : locale.getLanguage();
			if (getId().equals(lang)) {
				add(AttributeModifier.append("style", "font-weight:bold"));
			} else {
				add(AttributeModifier.remove("style"));
			}
		}

		@Override
		public void onClick() {
			Session.get().setLocale(new Locale(getId()));

		}
	}

}
