package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasText;

import de.bbcdaas.opendata.gwt.client.ViewNames;

public interface ISignupView  {

	HasClickHandlers getSubmitClickHandlers();

	HasText    getUsername();
	HasText    getEmail();
	HasText    getPassword();
	HasText    getConfirmPassword();
    HasText    getPasswordErrorBox();
    HasText    getEmailErrorBox();
    HasText getUsernameErrorBox();
    FocusWidget   getUsernameFocus ();
   FocusWidget  getConfirmPasswordFocus ();
   FocusWidget  getEmailFocus ();
   FocusWidget getPasswordFocus();
   ViewNames getViewName();
    
}
