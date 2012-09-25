package de.bbcdaas.opendata.gwt.client.layout.presenters;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.CookieConstants;
import de.bbcdaas.opendata.gwt.client.CookiesHandler;
import de.bbcdaas.opendata.gwt.client.Helpers;
import de.bbcdaas.opendata.gwt.client.layoutviews.TopView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ITopView;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = TopView.class)
public class TopPresenter extends BasePresenter<ITopView, AppEventBus> {

	HasWidgets loginContainer;

	public void onStart() {
		this.loginContainer = view.getloginContainer();
		String username = CookiesHandler.getCookie(CookieConstants.USERNAME);
		if (username == null || !Helpers.IsUserLoggedIn(username))
			setLoginView();
		else {
			SetLogoutScreen();
		}

		view.getHomepageLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eventBus.goToHomepage();
			}
		});
	}

	private void SetLogoutScreen() {

		loginContainer.clear();
		loginContainer.add((Widget) view.getLogoutView());
	}

	public void setLoginView() {
		loginContainer.clear();
		loginContainer.add((Widget) view.getLoginView());

	}

	public void onUserLoggedin(UserDetails user) {

		SetLogoutScreen();
		
	}

	public void onUserLoggedout(String username) {
		view.clearLoginFields();
		setLoginView();

	}
}
