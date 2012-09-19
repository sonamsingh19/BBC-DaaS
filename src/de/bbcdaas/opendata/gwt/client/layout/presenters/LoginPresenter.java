package de.bbcdaas.opendata.gwt.client.layout.presenters;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.CookieConstants;
import de.bbcdaas.opendata.gwt.client.CookiesHandler;
import de.bbcdaas.opendata.gwt.client.layoutviews.LoginView;
import de.bbcdaas.opendata.gwt.client.layoutviews.SignupView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILoginView;
import de.bbcdaas.opendata.gwt.client.services.ILoginServiceAsync;
import de.bbcdaas.opendata.gwt.client.services.IUserServiceAsync;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = LoginView.class)
public class LoginPresenter extends BasePresenter<ILoginView, AppEventBus> {

	public static final String presenterID = "Login";
	@Inject
	ILoginServiceAsync loginService;
	Boolean checkedCredentials;
	String username;

	SignupView signupView;

	@Inject
	IUserServiceAsync userService;
	UserDetails userDetails;

	@Override
	public void bind() {

		this.signupView = (SignupView) view.getSignupView();
		view.getViewKeyPressHandler().addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				setDefaultKeyForLogin(event);

			}
		});

		view.getLoginClickHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleLogin();
			}
		});

		view.getSignupClickHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				ClearAllFields();
				eventBus.setMainBody(signupView, signupView.getViewName()
						.toString(), presenterID);
			}
		});

	}

	protected void setDefaultKeyForLogin(KeyPressEvent event) {

		if (event.getCharCode() == KeyCodes.KEY_ENTER)
			handleLogin();

	}

	void setServices(ILoginServiceAsync loginservice,
			IUserServiceAsync userService) {
		this.loginService = loginservice;
		this.userService = userService;
	}

	void handleLogin() {

		this.username = view.getUsername();

		checkCredentials(username, view.getPassword());

	}

	void checkCredentials(String username, String password) {

		loginService.checkCredentials(username, password,
				new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						try {
							throw caught;
						} catch (Throwable e) {
							// TODO Auto-generated catch block

						}
					}

					@Override
					public void onSuccess(Boolean result) {

						checkedCredentials = result;
						if (checkedCredentials) {
							// getUser();
							loginUser();
						} else
							loginFailed();
					}
				});

	}

	void loginUser() {

		loginService.loginUser(username, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Boolean result) {
				if (result) {

					CookiesHandler
							.setcookie(CookieConstants.USERNAME, username);
					eventBus.userLoggedin(userDetails);

				}
			}

		});

	}

	private void getUser() {

		userService.getUser(username, new AsyncCallback<UserDetails>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(UserDetails result) {
				userDetails = result;

			}

		});

	}

	private void loginFailed() {
		view.loginFailed();
	}

	protected void ClearAllFields() {
		signupView.getConfirmPassword().setText("");
		signupView.getEmail().setText("");
		signupView.getUsername().setText("");
		signupView.getPassword().setText("");
	}

	public void onHandleHistory(String presenterID) {
		if (presenterID != null) {
			if (LoginPresenter.presenterID == presenterID) {
				// if(parameters.get(0)==ViewNames.Signup.toString())
				{
					ClearAllFields();
					eventBus.setMainBody(signupView, signupView.getViewName()
							.toString(), presenterID);
				}
			}
		}
	}
	/*
	 * public void onSignupCancelled(ISignupView newSignupView) {
	 * 
	 * view.setSignUpPanel(newSignupView); this.bind();
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
}
