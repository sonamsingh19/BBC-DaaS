package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.ListDataProvider;

import de.bbcdaas.opendata.gwt.shared.DataSet;

public interface IDataSetWidget {

	void setDataProvider(AsyncDataProvider<DataSet> provider);

	void setcolumns(int size, ArrayList<Column<List<String>, String>> columns);
	
	CellTable<ArrayList<String>> getCellTable();
	void update();
	void setDataProvider(ListDataProvider<ArrayList<String>> provider);
	
	IFilterScreen getFilterScreen();

}
