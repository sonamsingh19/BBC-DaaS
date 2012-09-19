package de.bbcdaas.opendata.gwt.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataSetDescription implements IsSerializable {

	String dataSetId;
	String name;
	ArrayList<String> tags;
	
	String description;
	
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DataSetDescription()
	{
		tags=new ArrayList<String>();
	}

	public String getDataSetId() {
		return dataSetId;
	}

	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}
	public void addTags(ArrayList<String> tags) {
		this.tags.addAll(tags);
	}
}
