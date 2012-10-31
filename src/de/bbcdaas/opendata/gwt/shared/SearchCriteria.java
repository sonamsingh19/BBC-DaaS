package de.bbcdaas.opendata.gwt.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class SearchCriteria implements Serializable {
	public SearchCriteria() {
		tags = new ArrayList<String>();
	}
	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	ArrayList<String> tags;

	

}
