package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.ListDataProvider;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDataSetWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFilterScreen;
import de.bbcdaas.opendata.gwt.client.resources.TableResources;
import de.bbcdaas.opendata.gwt.shared.DataSet;

public class DataSetWidget extends Composite implements IDataSetWidget {

	private static DataSetWidgetUiBinder uiBinder = GWT
			.create(DataSetWidgetUiBinder.class);
	@UiField(provided = true)
	CellTable<ArrayList<String>> datasetTable;
	@UiField
	SimplePager pager;
	@UiField
	SimplePanel panel;
	@UiField
	FilterScreen filterScreen;

	interface DataSetWidgetUiBinder extends UiBinder<Widget, DataSetWidget> {
	}

	public DataSetWidget() {
		CellTable.Resources resources = GWT.create(TableResources.class);
		datasetTable = new CellTable<ArrayList<String>>(15, resources);
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void setDataProvider(AsyncDataProvider<DataSet> provider) {

	}

	@Override
	public void setcolumns(int size,
			ArrayList<Column<List<String>, String>> columns) {

	}

	@Override
	public CellTable<ArrayList<String>> getCellTable() {
		return datasetTable;
	}

	@Override
	public void update() {

		pager.setDisplay(datasetTable);

	}

	@Override
	public void setDataProvider(ListDataProvider<ArrayList<String>> provider) {
		provider.addDataDisplay(datasetTable);
		pager.setDisplay(datasetTable);
	}

	@Override
	public IFilterScreen getFilterScreen() {
		return filterScreen;
	}
}
