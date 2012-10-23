package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DispDataSetWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public interface IMyDatasets {
	DispDataSetWidget addDatasets(DataSetInfo dataSetInfo);

	void deleteDataset(String datasetId);

	void clearDatasets();
}
