package de.bbcdaas.opendata.gwt.client;

import java.util.HashMap;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Event;
import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.annotation.InitHistory;
import com.mvp4g.client.annotation.Start;
import com.mvp4g.client.event.EventBus;

import de.bbcdaas.opendata.gwt.client.history.ContentViewHistoryConverter;
import de.bbcdaas.opendata.gwt.client.history.MainViewHistoryConverter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.AjaxLoaderPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.LoginPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.LogoutPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.MainPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.RootPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.SignupPresenter;
import de.bbcdaas.opendata.gwt.client.layout.presenters.TopPresenter;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters.ContentPanePresenter;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters.DataSetsPresenter;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters.MyProfilePresenter;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters.UploadPresenter;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Events(startPresenter = RootPresenter.class, historyOnStart = true)
public interface AppEventBus extends EventBus {

	@Start
	@Event(handlers = { RootPresenter.class, TopPresenter.class,
			MainPresenter.class, ContentPanePresenter.class }, bind = {
			LoginPresenter.class, LogoutPresenter.class, SignupPresenter.class,
			DataSetsPresenter.class, UploadPresenter.class,
			MyProfilePresenter.class })
	public void start();

	@Event(navigationEvent = true, handlers = MainPresenter.class, historyConverter = MainViewHistoryConverter.class, name = "view")
	void setMainBody(IsWidget body, String pageName, String presenterID);

	@Event(handlers = { RootPresenter.class, LogoutPresenter.class,
			TopPresenter.class, MainPresenter.class ,MyProfilePresenter.class})
	public void userLoggedin(UserDetails user);

	@Event(handlers = { RootPresenter.class, TopPresenter.class,
			MainPresenter.class })
	public void userLoggedout(String username);
	
	@Event(handlers={DataSetsPresenter.class,MyProfilePresenter.class})
	public void refreshDatasets();

	@InitHistory
	@Event(handlers = RootPresenter.class)
	public void init();
	
	

	@Event(handlers = { LoginPresenter.class, MainPresenter.class,
			ContentPanePresenter.class, UploadPresenter.class,
			DataSetsPresenter.class })
	public void handleHistory(String presenterID);

	@Event(navigationEvent = true, handlers = { ContentPanePresenter.class}, historyConverter = ContentViewHistoryConverter.class)
	public void setView(IsWidget widget, String pageName, String presenterID,
			HashMap<String, String> queryParameters);

	@Event(handlers = AjaxLoaderPresenter.class)
	public void appLoading(boolean isComplete, Widget relativeWidget);


	
	@Event(handlers={MainPresenter.class,DataSetsPresenter.class,ContentPanePresenter.class})
	public void goToHomepage();
}
