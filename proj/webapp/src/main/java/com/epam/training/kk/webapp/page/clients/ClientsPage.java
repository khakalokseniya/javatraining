package com.epam.training.kk.webapp.page.clients;



import java.util.Iterator;

import javax.inject.Inject;






















import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.epam.training.kk.dataaccess.model.Client;
import com.epam.training.kk.dataaccess.model.Order;
import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class ClientsPage extends AbstractPage{
	@Inject
	private ClientService clientService;

	@Override
	protected void onInitialize() {
		super.onInitialize();

		final Form<Object> clientForm = new Form<>("form-client");
		add(clientForm);
		ClientDataProvider clientDataProvider = new ClientDataProvider();
		clientForm.setOutputMarkupId(true);
		DataView<Client> dataView = new DataView<Client>("client-list", clientDataProvider, 10) {
			
			@Override
			protected void populateItem(Item<Client> item) {
				final Client client = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("phoneNumber", client.getPhoneNumber()));
				item.add(new Label("discont", client.getDiscont()));
				 AjaxLink alink = new AjaxLink<Client>("useDiscont-link", item.getModel()) {
				        @Override
				        public void onClick(AjaxRequestTarget target) {
							clientService.update(client.getPhoneNumber(), 0, client.getId());
							target.add(clientForm); 
				        }
				     };
				     alink.add(new Label("linklabel", "use discont"));
				     item.add(alink);
			}
		};
		clientForm.add(dataView);

		clientForm.add(new OrderByBorder<Object>("sortId", "id", clientDataProvider));
		clientForm.add(new OrderByBorder<Object>("sortDiscont", "discont", clientDataProvider));

		clientForm.add(new PagingNavigator("paging", dataView));
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
			return clientService.sort(first, count).iterator();
		}

		@Override
		public long size() {
			return clientService.getCount();
		}

		@Override
		public IModel<Client> model(Client object) {
			return new CompoundPropertyModel<>(object);
		}

	}
}


