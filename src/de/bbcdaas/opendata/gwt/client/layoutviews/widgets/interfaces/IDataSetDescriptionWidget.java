package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;

public interface IDataSetDescriptionWidget {
	String getName();

	String getDescription();

	ArrayList<String> getTags();

	HasClickHandlers getPublishClickHandlers();
	void setPublishBtnEnabled(boolean enabled);
	void setTags(ArrayList<String> tags);
	HasValueChangeHandlers<String> getNameValueChangeHandlers();
	
}
