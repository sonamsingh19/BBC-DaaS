package de.bbcdaas.opendata.gwt.client.layout.presenters;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.CookieConstants;
import de.bbcdaas.opendata.gwt.client.CookiesHandler;
import de.bbcdaas.opendata.gwt.client.layoutviews.LogoutView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILogoutView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters.MyProfilePresenter;
import de.bbcdaas.opendata.gwt.client.services.ILoginServiceAsync;
import de.bbcdaas.opendata.gwt.client.services.IUserServiceAsync;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = LogoutView.class)
public class LogoutPresenter extends BasePresenter<ILogoutView, AppEventBus> {
	@Inject
	ILoginServiceAsync loginService;

	
	@Inject
	IUserServiceAsync userService;

	@Override
	public void bind() {
		String username =CookiesHandler.getCookie(CookieConstants.USERNAME);
		view.getUserNameBox().setText(username);

		view.getAccountClickHandlers().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setMyProfileView();
				
			}
		});
		view.getLogoutClickHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				handleLogout();
			}

		});
	}

	protected void setMyProfileView() {
		
		eventBus.setMainBody(view.getMyProfileView(),view.getMyProfileView().getViewName().toString(), MyProfilePresenter.presenterID);
	}

	private void handleLogout() {
		loginService.logoutUser(CookiesHandler.getCookie(CookieConstants.USERNAME), new AsyncCallback<Boolean>() {

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

				eventBus.userLoggedout(CookiesHandler.getCookie(CookieConstants.USERNAME));
				CookiesHandler.DeleteCookie(CookieConstants.USERNAME);
			String usernameString=	CookiesHandler.getCookie(CookieConstants.USERNAME);
			}
		});
	}
	public void onUserLoggedin(UserDetails user) {
		String username =CookiesHandler.getCookie(CookieConstants.USERNAME);
		view.getUserNameBox().setText(username);
	}

}
