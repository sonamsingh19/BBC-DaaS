package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.AsyncDataProvider;

import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public interface IDataSetsPreviewWidget<T> {

	public void setSortingValues(ArrayList<String> sortingValues);

	public HasChangeHandlers getSortChangeHandlers();

	public int getSelectedSortIndex();

	public HasClickHandlers getRefreshHandler();

	public String getSelectedDataSetId();

	public CellTable<DataSetInfo> getCellTable();

	void setProvider(AsyncDataProvider<DataSetInfo> provider, int rowCount);

	void refresh();
	HasWidgets getTagPanel();

}
