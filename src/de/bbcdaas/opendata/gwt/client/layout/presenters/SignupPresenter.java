package de.bbcdaas.opendata.gwt.client.layout.presenters;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.ValidationErrors;
import de.bbcdaas.opendata.gwt.client.layoutviews.SignupView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ISignupView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.SignupCompleted;
import de.bbcdaas.opendata.gwt.client.services.IUserServiceAsync;

@Presenter(view = SignupView.class)
public class SignupPresenter extends BasePresenter<ISignupView, AppEventBus> {

	 
	@Inject
	IUserServiceAsync userService;

	@Override
	public void bind() {

		attachValidationHandlers();
		view.getSubmitClickHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Submit();
			}

		});
	}

	protected void Submit() {

		userService.userExists(view.getEmail().getText(),
				new AsyncCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean result) {
						if (result)
							// TODO CHANGE THIS
							Window.alert("User already exists");
						else {
							// TODO CREATE USER
							eventBus.setMainBody((IsWidget) GWT
									.create(SignupCompleted.class),"","");
						}

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void attachValidationHandlers() {

		view.getUsernameFocus().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				String username = view.getUsername().getText();
				if (validateUsername(username))
					view.getUsernameErrorBox().setText("");
				else {
					view.getUsernameErrorBox().setText(
							ValidationErrors.UsernameSmall);
				}

			}
		});
		view.getEmailFocus().addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				String email = view.getEmail().getText();
				if (!validateEmail(email))
					view.getEmailErrorBox().setText(
							ValidationErrors.InvalidEmail);

				else
					view.getEmailErrorBox().setText("");
			}
		});

		view.getEmailFocus().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String email = view.getEmail().getText();
				if (!validateEmail(email))
					view.getEmailErrorBox().setText(
							ValidationErrors.InvalidEmail);

				else
					view.getEmailErrorBox().setText("");
			}
		});

		view.getPasswordFocus().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String password = view.getPassword().getText();
				if (!validatePassword(password))
					view.getPasswordErrorBox().setText(
							ValidationErrors.passwordStrength);
				else {
					view.getPasswordErrorBox().setText("");
					attachConfirmPasswordHandlers();

				}

			}
		});

	}

	protected boolean vaildateConfirmPassword(String confirmPassword,
			String password) {
		return password.equals(confirmPassword);

	}

	protected boolean validatePassword(String password) {
		return password.length() < 6 ? false : true;

	}

	protected boolean validateEmail(String email) {
		String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (email.matches(emailPattern))
			return true;
		else
			return false;

	}

	protected boolean validateUsername(String username) {
		if (username.length() < 6 || username.contains(" "))

			return false;
		else
			return true;

	}

	private void attachConfirmPasswordHandlers() {
		view.getConfirmPasswordFocus().addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				String password = view.getPassword().getText();
				if (validatePassword(password)) {
					String confirmPassword = view.getConfirmPassword()
							.getText();

					if (vaildateConfirmPassword(confirmPassword, password)
							&& validatePassword(password))
						view.getPasswordErrorBox().setText("");
					else {
						view.getPasswordErrorBox().setText(
								ValidationErrors.PasswordDoesntMatch);
					}
				}
			}
		});

		view.getConfirmPasswordFocus().addFocusHandler(new FocusHandler() {

			@Override
			public void onFocus(FocusEvent event) {
				String password = view.getPassword().getText();
				if (validatePassword(password)) {
					String confirmPassword = view.getConfirmPassword()
							.getText();

					if (vaildateConfirmPassword(confirmPassword, password)
							&& validatePassword(password))
						view.getPasswordErrorBox().setText("");
					else {
						view.getPasswordErrorBox().setText(
								ValidationErrors.PasswordDoesntMatch);
					}
				}

			}
		});
		view.getConfirmPasswordFocus().addBlurHandler(new BlurHandler() {

			@Override
			public void onBlur(BlurEvent event) {
				String password = view.getPassword().getText();
				if (validatePassword(password)) {
					String confirmPassword = view.getConfirmPassword()
							.getText();

					if (vaildateConfirmPassword(confirmPassword, password))
						view.getPasswordErrorBox().setText("");
					else {
						view.getPasswordErrorBox().setText(
								ValidationErrors.PasswordDoesntMatch);
					}
				}

			}
		});
	}
	
	
}
