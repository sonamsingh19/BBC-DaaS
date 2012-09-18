package de.bbcdaas.opendata.gwt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataSetValues<T> implements IsSerializable {
	String columnId;
	ArrayList<T> values;

	public DataSetValues() {

		values = new ArrayList<T>();
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public ArrayList<T> getValues() {
		return values;
	}

	public void setValues(ArrayList<T> values) {
		this.values = values;
	}

	public void addValue(T value) {
		values.add(value);

	}
}
