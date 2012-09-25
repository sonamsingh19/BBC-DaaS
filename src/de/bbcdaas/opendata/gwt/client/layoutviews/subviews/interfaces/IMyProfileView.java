package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces;

import com.google.gwt.user.client.ui.HasWidgets;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyProfileNav;

public interface IMyProfileView  {
	ViewNames getViewName();
	IMyProfileNav getIMyProfileNav();
	HasWidgets getContainerPanel();
}
