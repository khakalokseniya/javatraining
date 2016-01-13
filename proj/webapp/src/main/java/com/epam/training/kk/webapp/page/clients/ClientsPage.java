package com.epam.training.kk.webapp.page.clients;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
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
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;
import com.epam.training.kk.webapp.page.cars.CarEditPage;

public class ClientsPage extends AbstractPage {
	@Inject
	private ClientService clientService;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Form<Object> clientForm = new Form<>("form-client");
		add(clientForm);
		clientForm.setOutputMarkupId(true);
		final WebMarkupContainer dataContainer = new WebMarkupContainer("dataContainer");
		final WebMarkupContainer searchContainer = new WebMarkupContainer("searchContainer");
		searchContainer.setVisible(false);
		final TextField<String> searchField = new TextField<String>("search-field");
		searchField.setRequired(true);
		clientForm.add(searchField);
		
		AjaxLink alink = new AjaxLink<Client>("button-search-form") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dataContainer.setVisible(false);
				searchContainer.setVisible(true);
				target.add(clientForm);
			}
		};
		clientForm.add(alink);
		
		dataContainer.setOutputMarkupId(true);
		clientForm.add(dataContainer);
		ClientDataProvider clientDataProvider = new ClientDataProvider();
		clientForm.add(searchContainer);
		DataView<Client> dataView = new DataView<Client>("client-list", clientDataProvider, 10) {

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
		dataContainer.add(dataView);

		dataContainer.add(new OrderByBorder<Object>("sortId", "id", clientDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortDiscont", "discont", clientDataProvider));
		dataContainer.add(new OrderByBorder<Object>("sortDistance", "distance", clientDataProvider));

		dataContainer.add(new PagingNavigator("paging", dataView));
		
		
		
		
		
		searchContainer.setOutputMarkupId(true);
		clientForm.add(searchContainer);
		ClDataProvider clDataProvider = new ClDataProvider(searchField.getInput());
		
		DataView<Client> searchView = new DataView<Client>("search-list", clDataProvider, 10) {
			
			@Override
			protected void populateItem(Item<Client> item) {
				final Client client = item.getModelObject();
				item.add(new Label("sid"));
				item.add(new Label("sphoneNumber", client.getPhoneNumber()));
				item.add(new Label("sdiscont", client.getDiscont()));
				item.add(new Label("sdistance", client.getDistance()));
				item.add(new Label("snumber", client.getNumberOfOrders()));
				item.add(new Link("sdetails-link") {
					
					@Override
					public void onClick() {
						setResponsePage(new DetailsPage(client));
					}
				});
			}
		};
		searchContainer.add(searchView);
		
		searchContainer.add(new OrderByBorder<Object>("ssortId", "sid", clDataProvider));
		searchContainer.add(new OrderByBorder<Object>("ssortDiscont", "sdiscont", clDataProvider));
		searchContainer.add(new OrderByBorder<Object>("ssortDistance", "sdistance", clDataProvider));
		
		searchContainer.add(new PagingNavigator("spaging", searchView));
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

			return clientService.sort(first, count, sort.isAscending(), (String) sort.getProperty()).iterator();
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

	/**
	 * @author МЫ
	 *
	 */
	private class ClDataProvider extends SortableDataProvider<Client, Object> {
		String searchString;

		public ClDataProvider(String searchString) {
			super();
			this.searchString = searchString;
			setSort("id", SortOrder.ASCENDING);
		}

		@Override
		public Iterator<? extends Client> iterator(long first, long count) {

			return clientService.search(searchString).iterator();
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
