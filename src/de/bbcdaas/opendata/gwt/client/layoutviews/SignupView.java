package de.bbcdaas.opendata.gwt.client.layoutviews;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ISignupView;

@Singleton
public class SignupView extends Composite implements ISignupView {

	private static SignupViewUiBinder uiBinder = GWT
			.create(SignupViewUiBinder.class);

	@UiField
	TextBox usernameBox;
	@UiField
	PasswordTextBox passwordBox;
	
	@UiField
	TextBox emailBox;
	@UiField
	PasswordTextBox confirmPasswordBox;
	@UiField Button submit;
	@UiField Label usernameErrorBox;
	@UiField Label emailErrorBox;
	@UiField Label passwordErrorBox;

	interface SignupViewUiBinder extends UiBinder<Widget, SignupView> {
	}

	public SignupView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
	}

	@Override
	public HasClickHandlers getSubmitClickHandlers() {

		return submit;
	}

	
	@Override
	public HasText getUsername() {

		return usernameBox;
	}

	@Override
	public HasText getEmail() {

		return emailBox;
	}

	@Override
	public HasText getPassword() {

		return passwordBox;
	}

	@Override
	public HasText getConfirmPassword() {

		return confirmPasswordBox;
	}

	@Override
	public HasText getPasswordErrorBox() {
		return passwordErrorBox;
	}

	@Override
	public HasText getEmailErrorBox() {
		
		return emailErrorBox;
	}

	@Override
	public HasText getUsernameErrorBox() {
		
		return usernameErrorBox;
	}

	

	@Override
	public FocusWidget getUsernameFocus() {
		return usernameBox;
	}

	@Override
	public FocusWidget getConfirmPasswordFocus() {
		return confirmPasswordBox;
	}

	@Override
	public FocusWidget getEmailFocus() {
		return emailBox;
	}

	@Override
	public FocusWidget getPasswordFocus() {
		return passwordBox;
	}

	@Override
	public ViewNames getViewName() {
		// TODO Auto-generated method stub
		return  ViewNames.Signup;
	}

	
	 
}
