package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DispDataSetWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public interface IMyDatasets {
	DispDataSetWidget addDatasets(DataSetInfo dataSetInfo);

	void deleteDataset(String datasetId);

	void clearDatasets();
	
	void setDefaultWidget(Widget widget);
}
