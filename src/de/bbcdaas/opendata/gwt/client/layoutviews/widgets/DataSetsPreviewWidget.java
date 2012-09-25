package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDataSetsPreviewWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public class DataSetsPreviewWidget extends Composite implements
		IDataSetsPreviewWidget<DataSetInfo> {

	private static DataSetsPreviewWidgetUiBinder uiBinder = GWT
			.create(DataSetsPreviewWidgetUiBinder.class);

	final SingleSelectionModel<DataSetInfo> selectionModel;

	@UiField
	SimplePager pager;
	@UiField
	ImageButton refreshBtn;
	@UiField
	ImageButton queryBtn;
	@UiField
	ListBox sortLBox;
	@UiField(provided = true)
	CellTable<DataSetInfo> cellTable = new CellTable<DataSetInfo>();
	@UiField TextBox searchTB;
	@UiField ImageButton goSearch;
	@UiField Anchor anchor;
	@UiField SimplePanel tagPanel;
	@UiField VerticalPanel datasetsPreviewPanel;


	interface DataSetsPreviewWidgetUiBinder extends
			UiBinder<Widget, DataSetsPreviewWidget> {
	}

	public DataSetsPreviewWidget() {

		initWidget(uiBinder.createAndBindUi(this));

		TextColumn<DataSetInfo> name = new TextColumn<DataSetInfo>() {

			@Override
			public String getValue(DataSetInfo object) {
				return object.getName();
			}
		};

		DateCell dateCell = new DateCell();
		Column<DataSetInfo, Date> datePostedColumn = new Column<DataSetInfo, Date>(
				dateCell) {

			@Override
			public Date getValue(DataSetInfo object) {
				return object.getDatePosted();
			}

		};
		datePostedColumn.setSortable(true);
		Column<DataSetInfo, Number> downloadColumn = new Column<DataSetInfo, Number>(
				new NumberCell()) {
			@Override
			public Number getValue(DataSetInfo parameter) {
				return parameter.getDownloads();
			}
		};

		downloadColumn.setSortable(true);
		cellTable.addColumn(name, "Name");
		cellTable.addColumn(downloadColumn, "Downloads");
		cellTable.addColumn(datePostedColumn, "Posted");

		selectionModel = new SingleSelectionModel<DataSetInfo>();
		cellTable.setSelectionModel(selectionModel);
		Label emptyTableWidget = new Label("No Datasets Uploaded");
		cellTable.setEmptyTableWidget(emptyTableWidget);
		datasetsPreviewPanel.getElement().getStyle().setProperty("minWidth", "500px");
		datasetsPreviewPanel.getElement().getStyle().setProperty("minHeight", "300px");
	
	}

	// A simple data type that represents a contact.
	@Override
	public void setProvider(AsyncDataProvider<DataSetInfo> provider,
			int rowCount) {
		provider.addDataDisplay(cellTable);
		pager.setDisplay(cellTable);
		cellTable.setRowCount(rowCount);
	}

	@Override
	public void setSortingValues(ArrayList<String> sortingValues) {
		for (String value : sortingValues) {
			sortLBox.addItem(value);
		}

	}

	@Override
	public int getSelectedSortIndex() {
		return sortLBox.getSelectedIndex();
	}

	@Override
	public HasChangeHandlers getSortChangeHandlers() {
		return sortLBox;
	}

	@Override
	public HasClickHandlers getRefreshHandler() {
		return refreshBtn;
	}

	@Override
	public String getSelectedDataSetId() {
		DataSetInfo info = selectionModel.getSelectedObject();
		return info.getId();
	}

	@Override
	public CellTable<DataSetInfo> getCellTable() {
		return cellTable;
	}

	@Override
	public void refresh() {

		cellTable.setVisibleRangeAndClearData(cellTable.getVisibleRange(), true);

	}

	@UiHandler("anchor")
	void onAnchorClick(ClickEvent event) {
		AdvancedSearchWidget advancedSearchWidget = GWT.create(AdvancedSearchWidget.class);
		advancedSearchWidget.center();
	}

	@Override
	public HasWidgets getTagPanel() {
	
		return tagPanel;
	}
}

// The list of data to display.

