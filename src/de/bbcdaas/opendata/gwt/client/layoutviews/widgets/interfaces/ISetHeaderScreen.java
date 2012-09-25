package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;

import de.bbcdaas.opendata.gwt.shared.DataSetColumn;

public interface ISetHeaderScreen {


	void setColumnInitialHeaders(	ArrayList<DataSetColumn> sampleValues);

	ArrayList<DataSetColumn> getColumnFinalHeaders();
	Boolean isFirstRowHeader();
	
	HasClickHandlers getSaveClickHandlers();

}
