package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface IDataSetDescriptionWidget {
	String getName();

	String getDescription();

	ArrayList<String> getTags();

	HasClickHandlers getPublishClickHandlers();

	void setTags(ArrayList<String> tags);
}
