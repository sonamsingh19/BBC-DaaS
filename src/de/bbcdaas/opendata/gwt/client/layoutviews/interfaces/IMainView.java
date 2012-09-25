package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.ContentPaneView;


public interface IMainView {
	void setWidget(IsWidget widget);
	ContentPaneView getContentPane();

}
