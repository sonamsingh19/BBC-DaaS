package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;



import com.google.gwt.event.dom.client.HasClickHandlers;

public interface IKeyValueWidget {

	String getKey();
	String getValue();
	HasClickHandlers getCancelBtnClickHandlers();
	void setIndex(int index);
	int getIndex();
}
