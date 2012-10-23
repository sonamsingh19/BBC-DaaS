package de.bbcdaas.opendata.gwt.server;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import java_cup.internal_error;

import au.com.bytecode.opencsv.CSVReader;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.bbcdaas.opendata.gwt.client.services.IDataSetService;
import de.bbcdaas.opendata.gwt.shared.ColumnType;
import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.DataSetValues;
import de.bbcdaas.opendata.gwt.shared.DataSets;
import de.bbcdaas.opendata.gwt.shared.SortingOptions;

public class DataSetServiceImpl extends RemoteServiceServlet implements
		IDataSetService {

	@Override
	public ArrayList<DataSetInfo> getDataSets(int start, int length,
			SortingOptions sortingOption) {

		ArrayList<DataSetInfo> resultArrayList = new ArrayList<DataSetInfo>();
		switch (sortingOption) {
		case DOWNLOADS:

			List<DataSetInfo> list = getSubList(DataSets.dataSetsInfos, start,
					length);
			resultArrayList = getArrayList(list);
			break;
		case LATEST:

			break;
		case OLDEST:

			break;

		default:
			break;
		}
		return resultArrayList;

	}

	private static ArrayList<DataSetInfo> SortByDownloads(
			ArrayList<DataSetInfo> unsorted) {
		Collections.sort(unsorted, new Comparator<DataSetInfo>() {

			@Override
			public int compare(DataSetInfo o1, DataSetInfo o2) {
				int num1 = o1.getDownloads();
				int num2 = o2.getDownloads();
				if (num1 < num2)
					return 1;
				else if (num1 > num2)
					return -1;
				else
					return 0;
			}
		});

		return unsorted;

	}

	private static ArrayList<DataSetInfo> getArrayList(List<DataSetInfo> list) {
		ArrayList<DataSetInfo> arrayList = new ArrayList<DataSetInfo>();
		for (DataSetInfo dataSetInfo : list) {
			arrayList.add(dataSetInfo);
		}
		return arrayList;

	}

	private List<DataSetInfo> getSubList(List<DataSetInfo> list, int start,
			int length) {
		ArrayList<DataSetInfo> arrayList = new ArrayList<DataSetInfo>();
		if (list.size() < length)
			length = list.size();
		for (int i = start; i < length; i++) {
			arrayList.add(list.get(i));
		}
		List<DataSetInfo> finalList = arrayList;
		return finalList;

	}

	@Override
	public ArrayList<DataSetColumn> getDataSetHeader(String id) {

		return DataSets.dataSets.get(id).getColumns();

	}

	@Override
	public void setDataSetHeader(String id, ArrayList<DataSetColumn> columns) {

		DataSets.dataSets.get(id).setColumns(columns);

	}

	@Override
	public DataSet getDataSet(String id, int start, int length) {
		return DataSets.dataSets.get(id);
	}

	@Override
	public ArrayList<ArrayList<String>> convertToRowWise(DataSet dataSet) {

		ArrayList<ArrayList<String>> columnDataArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> rowArrayList = new ArrayList<ArrayList<String>>();

		for (int index = 0; index < dataSet.getColumns().size(); index++) {

			columnDataArrayList.add(dataSet.getData()
					.get(dataSet.getColumns().get(index).getId()).getValues());

		}

		for (int i = 0; i < columnDataArrayList.size(); i++) {
			ArrayList<String> colwisewRow = columnDataArrayList.get(i);

			for (int j = 0; j < colwisewRow.size(); j++) {

				if (i == 0) {
					ArrayList<String> temp = new ArrayList<String>();
					rowArrayList.add(temp);
				}

				rowArrayList.get(j).add(colwisewRow.get(j));

			}
		}

		return rowArrayList;
	}

	@Override
	public int getDataSetCount() {
		return DataSets.dataSetsInfos.size();
	}

	@Override
	public HashMap<String, Integer> getTags() {

		HashMap<String, Integer> tags = DataSets.tags;

		return tags;
	}

	@Override
	public void setDescription(String id, DataSetDescription description) {

		DataSets.dataSetDescriptions.put(id, description);
		ArrayList<String> tags = description.getTags();
		for (String tag : tags) {
			if (!DataSets.tags.containsKey(tag))
				DataSets.tags.put(tag, 5);
			else {
				DataSets.tags.put(tag, DataSets.tags.get(tag) * 2);
			}

		}

	}

	@Override
	public DataSetDescription getDescription(String datasetId) {

		return DataSets.dataSetDescriptions.get(datasetId);
	}

	@Override
	public DataSet parseFile(String fileUrl, char delimiter) {

		Random randomGenerator = new Random();
		String dataSetId = randomGenerator.nextInt(10000) + "";
		DataSet dataSet = new DataSet();
		dataSet.setId(dataSetId);
		ArrayList<DataSetColumn> columns = new ArrayList<DataSetColumn>();
		HashMap<String, DataSetValues<String>> data = new HashMap<String, DataSetValues<String>>();
		try {
			FileReader fileReader = new FileReader(fileUrl);
			CSVReader reader = new CSVReader(fileReader, delimiter);
			List<String[]> entries = reader.readAll();

			// My CSV Code

			String[] values = entries.get(0);
			for (int i = 0; i < values.length; i++) {
				String columnId = randomGenerator.nextInt(10000) + "";

				DataSetColumn column = new DataSetColumn(columnId);
				column.setColumnType(ColumnType.TEXT);
				column.setName(values[i]);
				columns.add(column);

				ArrayList<String> columnValue = new ArrayList<String>();

				DataSetValues<String> dataSetValues = new DataSetValues<String>();
				dataSetValues.setColumnId(columnId);
				dataSetValues.setValues(columnValue);

				data.put(columnId, dataSetValues);

			}

			for (int j = 1; j < entries.size(); j++) {

				values = entries.get(j);
				for (int i = 0; i < columns.size(); i++) {

					data.get(columns.get(i).getId()).addValue(values[i]);

				}

			}
			reader.close();
			fileReader.close();

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		dataSet.setColumns(columns);
		dataSet.setData(data);

		putDatasetinStaticMemory(dataSet);
		return dataSet;

	}

	private void putDatasetinStaticMemory(DataSet dataSet) {

		DataSets.dataSets.put(dataSet.getId(), dataSet);

	}

	@Override
	public void setDatasetInfo(String id, DataSet dataset) {

		DataSetInfo dataSetInfo = new DataSetInfo(dataset.getName(), 1900);
		dataSetInfo.setId(dataset.getId());
		dataSetInfo.setName(dataset.getName());
		DataSets.dataSetsInfos.add(dataSetInfo);

	}

	@Override
	public void DeleteDataset(String datasetId) {
		DataSets.dataSets.remove(datasetId);
		int index = 0;
		for (DataSetInfo dataSetInfo : DataSets.dataSetsInfos) {
			if (dataSetInfo.getId().equalsIgnoreCase(datasetId)) {
				index = DataSets.dataSetsInfos.indexOf(dataSetInfo);
				break;
			}
		}
		DataSets.dataSetsInfos.remove(index);
		DataSets.dataSetDescriptions.remove(datasetId);

	}

}
