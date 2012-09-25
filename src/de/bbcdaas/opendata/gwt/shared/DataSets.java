package de.bbcdaas.opendata.gwt.shared;

import java.util.ArrayList;
import java.util.HashMap;


public class DataSets {

	public static HashMap<String, DataSet> dataSets;
	public static HashMap<String, DataSetDescription> dataSetDescriptions;
	public static ArrayList<DataSetInfo> dataSetsInfos;
	public static HashMap<String, Integer> tags;
	static {
		dataSetsInfos = new ArrayList<DataSetInfo>();
		dataSets = new HashMap<String, DataSet>();
		dataSetDescriptions = new HashMap<String, DataSetDescription>();
		tags = new HashMap<String, Integer>();
		tags.put("Health", 20);
		tags.put("Education", 20);
		tags.put("Goverment", 25);
		tags.put("Geo", 20);
		tags.put("Earth", 10);
		tags.put("Science", 20);
		tags.put("Agriculture", 12);
		tags.put("Population", 15);
		tags.put("Travel", 17);
		tags.put("Social", 19);
		tags.put("web 2.0", 13);
		tags.put("Parliament",22);
		tags.put("Open Data", 23);
		
	}
}
