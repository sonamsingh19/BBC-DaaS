package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.ViewNames;

public interface IUploadView {
	
	ViewNames getViewName();
	void setCurrentStep(int step);
	HasWidgets getContentPanel();
	Widget getViewAsWidget();

}
