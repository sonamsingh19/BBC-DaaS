package de.bbcdaas.opendata.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataSetColumn  implements IsSerializable {

	String id;
	ColumnType columnType;
	String name;

	
	 public DataSetColumn(String id)
	 {
		 this.id=id;
		 
		 
	 }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public void setColumnType(ColumnType columnType) {
		this.columnType = columnType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 public DataSetColumn()
	 {}
 
	 
}
