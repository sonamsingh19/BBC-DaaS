package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DataSetsPreviewWidget;

public interface IDataSetsView {

	ViewNames getViewName();

	Widget getViewAsWidget();

	DataSetsPreviewWidget getDataSetsPreviewWidget();

	HasWidgets getContentPanel();
}
