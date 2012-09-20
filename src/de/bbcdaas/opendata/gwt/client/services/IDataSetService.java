package de.bbcdaas.opendata.gwt.client.services;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.SortingOptions;

@RemoteServiceRelativePath("DataSetService")
public interface IDataSetService extends RemoteService {

	public ArrayList<DataSetInfo> getDataSets(int start, int length,
			SortingOptions sortingOption);

	public ArrayList<DataSetColumn> getDataSetHeader(String id);

	public void setDataSetHeader(String id, ArrayList<DataSetColumn> columns);

	public DataSet getDataSet(String id, int start, int length);

	public ArrayList<ArrayList<String>> convertToRowWise(DataSet dataSet);
	public int getDataSetCount();
	
	public HashMap<String,Integer> getTags();
	
	public void setDescription(String id,DataSetDescription decription);
	
	public DataSetDescription getDescription(String datasetId);
	
	public DataSet parseFile(String fileUrl,char delimiter);
	
	public void setDatasetInfo(String id,DataSet datasetInfo);
}
