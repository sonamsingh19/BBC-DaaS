package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import com.google.gwt.event.dom.client.HasClickHandlers;

import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public interface IDispDataSetWidget {
	HasClickHandlers getDeleteBtnClickHandlers();
	void setDataset(DataSetInfo dataSetInfo);
	String getDatasetId();

}
