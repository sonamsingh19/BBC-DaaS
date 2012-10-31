package de.bbcdaas.opendata.gwt.client.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.SearchCriteria;
import de.bbcdaas.opendata.gwt.shared.SortingOptions;

public interface IDataSetServiceAsync {

	void getDataSets(int start, int length,SortingOptions sortingOption,
			AsyncCallback<ArrayList<DataSetInfo>> callback);

	void getDataSetHeader(String id,AsyncCallback< ArrayList<DataSetColumn> > asyncCallback);

	void setDataSetHeader(String id, ArrayList<DataSetColumn> columns,
			AsyncCallback<Void> callback);

	void getDataSet(String id, int start, int length,
			AsyncCallback<DataSet> callback);

	void convertToRowWise(DataSet dataSet,
			AsyncCallback<ArrayList<ArrayList<String>>> callback);

	void getDataSetCount(AsyncCallback<Integer> callback);

	void getTags(AsyncCallback<HashMap<String, Integer>> callback);

	void setDescription(String id, DataSetDescription description,
			AsyncCallback<Void> callback);

	void getDescription(String datasetId,
			AsyncCallback<DataSetDescription> callback);

	void parseFile(String fileUrl, char delimiter,
			AsyncCallback<DataSet> callback);

	void setDatasetInfo(String id, DataSet datasetInfo,
			AsyncCallback<Void> callback);

	void DeleteDataset(String datasetId, AsyncCallback<Void> callback);

	void getDataSetsBySearch(int start, int length,
			SearchCriteria searchCriteria,
			AsyncCallback<ArrayList<DataSetInfo>> callback);

	

}
