package de.bbcdaas.opendata.gwt.client.layoutviews;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILoginView;

@Singleton
public class LoginView extends Composite implements ILoginView {

	SignupView signupView;

	private static LoginViewUiBinder uiBinder = GWT
			.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	@Inject
	public LoginView(SignupView signUpView) {
		this.signupView = signUpView;
		initWidget(uiBinder.createAndBindUi(this));

	}
	@UiField
	TextBox username;

	@UiField
	PasswordTextBox password;
	@UiField
	Button signup;
	@UiField
	FocusPanel focusPanel;
	@UiField Button login;

	@Override
	public String getUsername() {
		return username.getText();
	}

	@Override
	public String getPassword() {
		return password.getValue();
	}

	@Override
	public HasClickHandlers getLoginClickHandlers() {
		return login;
	}

	@Override
	public void loginFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public HasClickHandlers getSignupClickHandlers() {

		return signup;
	}

	@Override
	public IsWidget getSignupView() {
		return signupView;
	}

	@Override
	public HasKeyPressHandlers getViewKeyPressHandler() {

		return focusPanel;

	}

	@Override
	public void clearFields() {
		username.setText("");
		password.setText("");
		
	}
	



}
