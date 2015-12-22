package com.epam.training.kk.webapp.page.clients;



import javax.inject.Inject;


import com.epam.training.kk.services.ClientService;
import com.epam.training.kk.webapp.page.abstractPage.AbstractPage;

public class ClientsPage extends AbstractPage{
	@Inject
	private ClientService clientService;
}
	/*@Override
	protected void onInitialize() {
		super.onInitialize();

		ClientDataProvider clientDataProvider = new ClientDataProvider();
		DataView<User> dataView = new DataView<User>("users-list", usersDataProvider, 3) {
			@Override
			protected void populateItem(Item<User> item) {
				final User user = item.getModelObject();
				item.add(new Label("id"));
				item.add(new Label("fName", user.getFirstName()));
				item.add(new Label("lName", user.getLastName()));
				item.add(new Label("email"));

				item.add(new Link("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new UserEditPage(user));
					}
				});

			}
		};
		add(dataView);

		add(new OrderByBorder<Object>("sortId", "id", usersDataProvider));
		add(new OrderByBorder<Object>("sortfName", "firstName", usersDataProvider));

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
			// TODO sort in service
			return clientService.getAll(first, count).iterator();
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


*/