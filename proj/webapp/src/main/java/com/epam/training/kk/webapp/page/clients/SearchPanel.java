package com.epam.training.kk.webapp.page.clients;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.epam.training.kk.dataaccess.model.Client;

//public class SearchPanel extends Panel{
//
//    private SearchPanelDelegate delegate;
//    private String searchString;
//    private WebMarkupContainer resulttable;
//    private DataView dataView;
//     
//    public SearchPanel(String id, final SearchPanelDelegate delegate) {
//        super(id);
//        this.delegate = delegate;
//         
//        // create form and add form to the search panel
//        Form<?> form = new SearchForm("form");
//        add(form);
//         
//        // create markup container around the result table
//        // this is necessary to hide the result list before the form submit
//        this.resulttable = new WebMarkupContainer("resulttable");
//        this.resulttable.setVisible(false);
//        add(resulttable);
//         
//        // init the hitlist with an empty dataprovider
//        resultlist = new ClDataProvider();
//         
//        // create data view component
//        this.dataView = new DataView<Client>("resultlist", resultlist) {
//            @Override
//            protected void populateItem(Item item) {
//                 
//                // get customer object for row
//                final Client client = (Client) item.getModelObject();
//                 
//                // create a link for column 1
//                SearchPanelLink link = new SearchPanelLink("select");
//                // the customer object to the link.
//                //This customer object will be returned when the row is selected
//                link.setClient(client);
//             
//                // Add link and labels to the data view
//                item.add(link);
//                item.add(new Label("phone number",client.getPhoneNumber()));
//                item.add(new Label("discont", client.getDiscont()));
//            }
//             
//        };
//         
//        // add data view to search panel
//        this.resulttable.add(dataView);
//         
//        // Filter the feedback messages
//        // Only messages which belongs to the current form will be displayed
//        FeedbackPanel feedback = new FeedbackPanel("feedback");
//        feedback.setFilter(new ContainerFeedbackMessageFilter(form));
//        add(feedback);
//    }
//     
//     
//    public String getSearchString() {
//        return searchString;
//    }
// 
//    public void setSearchString(String searchString) {
//        this.searchString = searchString;
//    }
// 
//    public ClientDataProvider getResultlist() {
//        return resultlist;
//    }
// 
//    public void setResultlist(ClientDataProvider resultlist) {
//        this.resultlist = resultlist;
//    }
// 
//    public final class SearchForm extends Form {
// 
//        // The form seach field
//        private TextField<String> searchfield;
//         
//        public SearchForm(String id) {
//            super(id);
//             
//            // init seachfield
//            searchfield = new TextField<String>("seachfield", new PropertyModel<String>(SearchPanel.this, "searchString"));
//            // set field required
//            searchfield.setRequired(true);
//            // add searchfield to search form
//            add(searchfield);          
//        }
// 
//        /**
//         * This method is executed the the search button was pressed
//         */
//        @Override
//        protected void onSubmit() {
//            // invoke delegate method
//            // the search is submitted and the delegate object will be informed
//            delegate.searchSubmitted(SearchPanel.this);
//            // make the result table visible
//            resulttable.setVisible(true);
//            super.onSubmit();
//        }  
//    }
// 
//    /**
//     * Link implementation for the table rows
//     * @author manuel.fritsch
//     *
//     */
//    public class SearchPanelLink extends Link<String> {
// 
//        private Client client;
// 
//        public SearchPanelLink(String id) {
//            super(id);
//        }
//         
//        /**
//         * This method will be executed when the select link
//         * used in the result page
//         */
//        @Override
//        public void onClick() {
// 
//            // invoke delegate method
//            // a customer was the selected an the delegate object will be informed
//            if(delegate != null) {
//                delegate.clientSelected(SearchPanel.this, client);
//            }
//        }
// 
//        /**
//         * @return the customer
//         */
//        public Client getClient() {
//            return client;
//        }
// 
//        /**
//         * @param customer the customer to set
//         */
//        public void setClient(Client client) {
//            this.client = client;
//        }
//        
//        private class ClDataProvider implements IDataProvider<Client> {
//
//    		public ClDataProvider() {
//    			super();
//    			
//    		}
//
//    		@Override
//    		public Iterator<? extends Client> iterator(long first, long count) {
//
//    		
//    			return clientService.sort(first, count).iterator();
//    		}
//
//    		@Override
//    		public long size() {
//    			return clientService.getCount();
//    		}
//
//    		@Override
//    		public IModel<Client> model(Client object) {
//    			return new CompoundPropertyModel<>(object);
//    		}
//
//    		public void detach() {
//
//    		}
//    	}
//    }
//}
//
