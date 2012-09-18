package de.bbcdaas.opendata.gwt.client.layout.presenters;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.CookieConstants;
import de.bbcdaas.opendata.gwt.client.CookiesHandler;
import de.bbcdaas.opendata.gwt.client.Helpers;
import de.bbcdaas.opendata.gwt.client.layoutviews.MainView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IMainView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.ContentPaneView;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = MainView.class)
public class MainPresenter extends BasePresenter<IMainView, AppEventBus> {
	public final String presenterID = "Main";

	ContentPaneView contentPainView;

	public void onStart() {

		this.contentPainView = view.getContentPane();
		String username = CookiesHandler.getCookie(CookieConstants.USERNAME);
		if (username == null || !Helpers.IsUserLoggedIn(username)) {
			eventBus.setMainBody(contentPainView, contentPainView.getViewName()
					.toString(), this.presenterID);
		}

		else {
			// TODORemove this
			UserDetails userDetails = new UserDetails();
			userDetails.setUsername(username);
			eventBus.userLoggedin(userDetails);
		}
	}

	public void onSetMainBody(IsWidget mainBody, String pageName,
			String presenterID) {
		view.setWidget(mainBody);
	}

	public void onUserLoggedout(String username) {
		eventBus.setMainBody(contentPainView, contentPainView.getViewName()
				.toString(), this.presenterID);

	}

	public void onUserLoggedin(UserDetails user) {
		eventBus.setMainBody(contentPainView, contentPainView.getViewName()
				.toString(), this.presenterID);
	}

	public void onGoToHomepage() {
		eventBus.setMainBody(contentPainView, contentPainView.getViewName()
				.toString(), this.presenterID);
	}

	public void onHandleHistory(String presenterID) {
		if (presenterID != null) {
			if (this.presenterID.equals(presenterID)) {
				{
					eventBus.setMainBody(contentPainView, contentPainView
							.getViewName().toString(), this.presenterID);
				}
			}
		}
	}
}
