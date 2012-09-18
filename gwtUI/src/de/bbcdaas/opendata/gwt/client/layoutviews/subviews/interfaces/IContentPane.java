package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.DataSetsView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.UploadView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.INavigationPane;

public interface IContentPane {
	ViewNames getViewName();
	void setContainerPanel(IsWidget widget);
	
	DataSetsView getDataSetsView();
	UploadView getUploadView();
	INavigationPane getNavigationPane();
}
