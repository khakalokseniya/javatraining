package com.epam.training.kk.webapp.page.clients;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class ClientsPage extends AbstractPage {
	@Inject
	private ClientService clientService;
	String search = "";

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		dataContainer.setOutputMarkupId(true);
		final TextField<String> searchField = new TextField<String>("search-field", new Model<String>());
		Form<?> form = new Form<Void>("search-form");
		add(form);
		form.add(searchField);
		searchField.setOutputMarkupId(true);
		AjaxButton alink = new AjaxButton("button-search-form") {
			@Override
			public void onSubmit(AjaxRequestTarget target, Form form) {
				if (searchField.getModelObject() == null) {
					search = "";
				} else {
					search = searchField.getModelObject();
				}
				target.add(dataContainer);
			}
		};
		form.add(alink);

		ClientDataProvider clientDataProvider = new ClientDataProvider();

		final DataView dataView = new DataView<Client>("client-list", clientDataProvider, 10) {

			@Override
			protected void populateItem(Item<Client> item) {
				final Client client = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("phoneNumber", client.getPhoneNumber()));
				item.add(new Label("discont", client.getDiscont()));
				item.add(new Label("distance", client.getDistance()));
				item.add(new Label("number", client.getNumberOfOrders()));
				item.add(new Link("details-link") {

					@Override
					public void onClick() {
						setResponsePage(new DetailsPage(client));
					}
				});
			}
		};
		dataView.setOutputMarkupId(true);
		dataContainer.add(dataView);

		dataContainer.add(new OrderByBorder<Object>("sortId", "id", clientDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortDiscont", "discont", clientDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortDistance", "distance", clientDataProvider));

		add(dataContainer);
		add(new PagingNavigator("paging", dataView));

	}

	private class ClientDataProvider extends SortableDataProvider<Client, Object> {
		public ClientDataProvider() {
			super();
			setSort("id", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends Client> iterator(long first, long count) {

			SortParam<Object> sort = getSort();
			ISortState<Object> sortState = getSortState();
			SortOrder currentSort = sortState.getPropertySortOrder(sort.getProperty());

			System.out.println(sort.getProperty() + ":" + currentSort.name());

			return clientService.sort(first, count, sort.isAscending(), (String) sort.getProperty(), search).iterator();
		}

		@Override
		public long size() {
			return clientService.getCount();
		}

		@Override
		public IModel<Client> model(Client object) {
			return new CompoundPropertyModel<>(object);
		}

		public void detach() {

		}
	}

}
