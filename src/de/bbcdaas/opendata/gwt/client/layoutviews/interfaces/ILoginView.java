package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.IsWidget;

public interface ILoginView {

	String getUsername();

	String getPassword();

	HasClickHandlers getLoginClickHandlers();

	void loginFailed();

	HasClickHandlers getSignupClickHandlers();

    IsWidget getSignupView();
  

	HasKeyPressHandlers getViewKeyPressHandler();
	void clearFields();
	
}
