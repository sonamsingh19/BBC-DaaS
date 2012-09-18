package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasWidgets;

public interface ITopView {
	HasWidgets getloginContainer();

	ILoginView getLoginView();

	ILogoutView getLogoutView();
    void clearLoginFields();
    HasClickHandlers getHomepageLink();


}
