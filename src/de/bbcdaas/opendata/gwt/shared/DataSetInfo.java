package de.bbcdaas.opendata.gwt.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataSetInfo implements IsSerializable {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String id;
	String name;

	

	// String [] tags;
	int downloads;
	Date datePosted;

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public DataSetInfo(String name, int downloads, Date datePosted) {

		this.name = name;
		this.downloads = downloads;
		this.datePosted = datePosted;
	}

	public DataSetInfo() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDownloads() {
		return downloads;
	}

	public void setDownloads(int downloads) {
		this.downloads = downloads;
	}

}
