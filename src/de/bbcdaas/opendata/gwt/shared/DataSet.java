package de.bbcdaas.opendata.gwt.shared;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataSet implements IsSerializable {

	String id;
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	HashMap<String, DataSetValues<String>> data;
	ArrayList<DataSetColumn> columns;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public void setColumns(ArrayList<DataSetColumn> columns) {
		this.columns = columns;
	}

	public ArrayList<DataSetColumn> getColumns() {
		return columns;
	}

	public HashMap<String, DataSetValues<String>> getData() {
		return data;
	}

	public void setData(HashMap<String, DataSetValues<String>> data) {
		this.data = data;
	}

	public DataSet(HashMap<String, DataSetValues<String>> data,
			ArrayList<DataSetColumn> columns) {
		this.data = data;
		this.columns = columns;
	}

	public DataSet() {
	}

}
