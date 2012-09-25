package de.bbcdaas.opendata.gwt.client.layout.presenters;


import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.RootView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IRootView;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = RootView.class)
public class RootPresenter extends BasePresenter<IRootView, AppEventBus> {

	public void onStart() {
	
	
	}

	public void onUserLoggedin(UserDetails user) {

	}

	public void onUserLoggedout(String username) {

	}
	public void onInit()
	{
		
		
	}

}