package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.DataSetsView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IDataSetsView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DataSetWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.FilterWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.TagCloud.TagCloud;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.TagCloud.WordTag;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDataSetsPreviewWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFilterScreen;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFilterWidget;
import de.bbcdaas.opendata.gwt.client.services.IDataSetServiceAsync;
import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.SortingOptions;
import de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations.DateOperations;
import de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations.FilterUtility;
import de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations.IntOperations;
import de.bbcdaas.opendata.gwt.shared.Enums.FilterOperations.TextOperations;

@Presenter(view = DataSetsView.class)
public class DataSetsPresenter extends
		BasePresenter<IDataSetsView, AppEventBus> {
	public static final String presenterID = "DataSet";

	ArrayList<String> sortingOptions;
	@Inject
	IDataSetServiceAsync dataServiceAsync;
	final String dataSetIdString = "datasetId";

	SortingOptions defaultSortingOption;
	AsyncDataProvider<DataSetInfo> provider;
	DataSetWidget dataSetWidget;
	IFilterScreen filterScreen;

	IDataSetsPreviewWidget<DataSetInfo> dataSetPreviewWidget;
	HashMap<Integer, FilterWidget> filters;
	HashMap<Integer, FilterWidget> appliedFilters;
	DataSet dataSet;
	List<ArrayList<String>> rowWiseDataSet;
	ListDataProvider<ArrayList<String>> dataProvider;

	public void onStart() {

	}

	@Override
	public void bind() {
		setDataSetsPriviewWidget();
		addWidgetToView((Widget) dataSetPreviewWidget);
		filters = new HashMap<Integer, FilterWidget>();
		appliedFilters = new HashMap<Integer, FilterWidget>();
	}

	private void setDataSetsPriviewWidget() {
		dataSetPreviewWidget = view.getDataSetsPreviewWidget();
		setTagCloud();

		dataSetPreviewWidget.getCellTable().addCellPreviewHandler(
				new Handler<DataSetInfo>() {
					long lastClick = -1000;

					@Override
					public void onCellPreview(
							CellPreviewEvent<DataSetInfo> event) {
						long clictAt = System.currentTimeMillis();
						if (Event.getTypeInt(event.getNativeEvent().getType()) == Event.ONCLICK) {
							if (clictAt - lastClick < 300) { // dblclick on 2
																// clicks
																// detected
																// within 300
																// ms

								showDataSet(dataSetPreviewWidget
										.getSelectedDataSetId());
							}
							lastClick = System.currentTimeMillis();
						}
					}
				});
		defaultSortingOption = SortingOptions.DOWNLOADS;

		provider = new AsyncDataProvider<DataSetInfo>() {

			@Override
			protected void onRangeChanged(HasData<DataSetInfo> display) {
				eventBus.appLoading(false, view.getViewAsWidget());
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();

				AsyncCallback<ArrayList<DataSetInfo>> callback = new AsyncCallback<ArrayList<DataSetInfo>>() {

					@Override
					public void onSuccess(ArrayList<DataSetInfo> result) {

						updateRowData(start, result);
						eventBus.appLoading(true, view.getViewAsWidget());
					}

					@Override
					public void onFailure(Throwable caught) { // TODO

					}
				};
				dataServiceAsync.getDataSets(start, length,
						defaultSortingOption, callback);

			}
		};

		setDatasetCount();

		sortingOptions = new ArrayList<String>();
		sortingOptions.add("Downloads");
		sortingOptions.add("Latest");
		sortingOptions.add("Oldest");
		dataSetPreviewWidget.setSortingValues(sortingOptions);

		dataSetPreviewWidget.getRefreshHandler().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						refreshDataSets();

					}

				});

		dataSetPreviewWidget.getSortChangeHandlers().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						onSortOptionSelected();

					}
				});

	}

	private void setTagCloud() {
		dataServiceAsync.getTags(new AsyncCallback<HashMap<String, Integer>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(HashMap<String, Integer> result) {

				TagCloud tagCloud = new TagCloud();
				tagCloud.setColored(true);
				int i=0;
				for (String key : result.keySet()) {
					int frequency = result.get(key);
					String word = key;
					WordTag tag = new WordTag(word);
					tag.setNumberOfOccurences(frequency);
					String link = Window.Location.getPath();
					tag.setLink(link);
  
					if((i%3)==0) tag.setOrientation(2);
					tagCloud.addWord(tag);
			i=i+3;

				}
				dataSetPreviewWidget.getTagPanel().clear();
				dataSetPreviewWidget.getTagPanel().add(tagCloud);
			}
		});

	}

	private void setDatasetCount() {
		dataServiceAsync.getDataSetCount(new AsyncCallback<Integer>() {
			int datasetsCount = 0;

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Integer result) {

				datasetsCount = result;
				dataSetPreviewWidget.setProvider(provider, datasetsCount);

			}
		});

	}

	private void addWidgetToView(Widget widget) {
		view.getContentPanel().clear();
		view.getContentPanel().add(widget);

	}

	protected void showDataSet(String selectedDataSetId) {

		addToHistory(selectedDataSetId);
		eventBus.appLoading(false, view.getViewAsWidget());
		dataSetWidget = GWT.create(DataSetWidget.class);
		filterScreen = dataSetWidget.getFilterScreen();
		dataServiceAsync.getDataSet(selectedDataSetId, 0, 10,
				new AsyncCallback<DataSet>() {

					@Override
					public void onSuccess(DataSet result) {
						eventBus.appLoading(true, dataSetWidget);
						dataSet = result;
						setDataSet(result);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

		filterScreen.getAddFilterClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addNewFilter();

					}
				});
		addWidgetToView(dataSetWidget);
	}

	private void addToHistory(String selectedDataSetId) {
		HashMap<String, String> queryString = new HashMap<String, String>();
		queryString.put(dataSetIdString, selectedDataSetId);
		eventBus.setView(view.getViewAsWidget().asWidget(), view.getViewName()
				.toString(), DataSetsPresenter.presenterID, queryString);
	}

	protected void addNewFilter() {
		final FilterWidget widget = GWT.create(FilterWidget.class);
		widget.setColumns(dataSet.getColumns());
		widget.setIndex(filters.size());
		filters.put(widget.getIndex(), widget);
		filterScreen.AddNewFilter(widget);

		ArrayList<String> operations = getOperationsforSelectedCol(widget);
		widget.setOperations(operations);

		widget.getApplyFilterClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						int index = widget.getIndex();
						if (!appliedFilters.containsKey(index))

						{
							appliedFilters.put(index, widget);
							IFilterWidget filterWidget = filters.get(index);

							DataSetColumn column = filterWidget
									.getSelectedColumn();
							String operation = filterWidget
									.getSelectedOperation();
							String operand = filterWidget.getOperandValue();
							executeQuery(dataProvider.getList(), column,
									operation, operand);
						} else {

							for (FilterWidget filterWidget : appliedFilters
									.values()) {
								List<ArrayList<String>> filtered = rowWiseDataSet;
								DataSetColumn column = filterWidget
										.getSelectedColumn();
								String operation = filterWidget
										.getSelectedOperation();
								String operand = filterWidget.getOperandValue();
								filtered = executeQuery(filtered, column,
										operation, operand);
							}
						}

					}
				});

		widget.getColumnSelectedHandlers().addChangeHandler(
				new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {

						ArrayList<String> operations = getOperationsforSelectedCol(widget);
						widget.setOperations(operations);

					}

				});
		widget.getCancelClickHandlers().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				List<ArrayList<String>> filteredResult = rowWiseDataSet;
				int index = widget.getIndex();
				filterScreen.DeleteFilter(index);
				filters.remove(index);
				if (appliedFilters.containsKey(index)) {
					appliedFilters.remove(index);
					if (appliedFilters.size() == 0)
						dataProvider.setList(filteredResult);
					else {

						for (FilterWidget filterWidget : appliedFilters
								.values()) {
							DataSetColumn column = filterWidget
									.getSelectedColumn();
							String operation = filterWidget
									.getSelectedOperation();
							String operand = filterWidget.getOperandValue();
							filteredResult = executeQuery(filteredResult,
									column, operation, operand);
						}

					}

				}
			}
		});

	}

	protected ArrayList<ArrayList<String>> executeQuery(
			List<ArrayList<String>> list, DataSetColumn column,
			String operation, String operand) {
		int columnIndex = dataSet.getColumns().indexOf(column);
		ArrayList<ArrayList<String>> filteredResult = new ArrayList<ArrayList<String>>();
		switch (column.getColumnType()) {

		case NUMBER:

			IntOperations intOprtn = IntOperations.valueOf(operation);
			filteredResult = FilterUtility.filter(
					list,
					FilterUtility.getIntOperatorPredicate(intOprtn,
							Double.parseDouble(operand)), columnIndex);
			dataProvider.setList(filteredResult);
			break;
		case TEXT:

			TextOperations textOprtn = TextOperations.valueOf(operation);
			filteredResult = FilterUtility.filter(list,
					FilterUtility.getTextOperatorPredicate(textOprtn, operand),
					columnIndex);
			dataProvider.setList(filteredResult);

			break;
		case DATE:
			DateOperations dateOprtn = DateOperations.valueOf(operation);
			filteredResult = FilterUtility.filter(list,
					FilterUtility.getDateOperatorPredicate(dateOprtn, operand),
					columnIndex);
			dataProvider.setList(filteredResult);

			break;

		default:
			break;
		}

		return filteredResult;

	}

	private ArrayList<String> getOperationsforSelectedCol(FilterWidget widget) {
		ArrayList<String> operations = new ArrayList<String>();

		switch (widget.getSelectedColumn().getColumnType()) {
		case TEXT:
			for (TextOperations operation : TextOperations.values()) {
				operations.add(operation.toString());
			}
			break;
		case NUMBER:
			for (IntOperations operation : IntOperations.values()) {
				operations.add(operation.toString());
			}
			break;
		case DATE:
			for (DateOperations operation : DateOperations.values()) {
				operations.add(operation.toString());
			}
			break;
		default:
			break;
		}

		return operations;

	}

	protected void setDataSet(DataSet result) {
		filterScreen.setColumns(dataSet.getColumns());
		filterScreen.getDownloadTypeLB().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {

				filterScreen.setDataSetId(dataSet.getId(),
						filterScreen.getSelectedDownloadFormat());
			}
		});
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(),
				filterScreen.getDownloadTypeLB());
		filterScreen.setDataSetName(dataSet.getName());
		
		dataServiceAsync.getDescription(dataSet.getId(), new AsyncCallback<DataSetDescription>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(DataSetDescription result) {
					filterScreen.setMetadata(result);

				
			}
		});
	
		CellTable<ArrayList<String>> datasetTable = dataSetWidget
				.getCellTable();

		ArrayList<ArrayList<String>> columnDataArrayList = new ArrayList<ArrayList<String>>();

		for (int index = 0; index < result.getColumns().size(); index++) {
			final int value = index;
			TextCell c2 = new TextCell();
			Column<ArrayList<String>, String> column = new Column<ArrayList<String>, String>(
					c2) {

				@Override
				public String getValue(ArrayList<String> object) {

					return object.get(value);

				}

			};

			columnDataArrayList.add(result.getData()
					.get(result.getColumns().get(index).getId()).getValues());

			datasetTable.addColumn(column, result.getColumns().get(index)
					.getName());
		}
		eventBus.appLoading(false, dataSetWidget);

		dataServiceAsync.convertToRowWise(result,
				new AsyncCallback<ArrayList<ArrayList<String>>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(
							ArrayList<ArrayList<String>> rowWiseData) {

						rowWiseDataSet = rowWiseData;
						eventBus.appLoading(true, dataSetWidget);
						dataProvider = new ListDataProvider<ArrayList<String>>();

						dataSetWidget.setDataProvider(dataProvider);

						dataProvider.setList(rowWiseData);

					}
				});

		/*
		 * dataProvider.addDataDisplay(datasetTable);
		 * datasetTable.setRowCount(rowArrayList.size());
		 * datasetTable.setRowData(rowArrayList);
		 */

	}

	protected void onSortOptionSelected() {
		int index = dataSetPreviewWidget.getSelectedSortIndex();
		switch (index) {
		case 0:
			defaultSortingOption = SortingOptions.DOWNLOADS;

			break;

		default:
			break;
		}

	}

	protected void refreshDataSets() {
		dataSetPreviewWidget.refresh();
		setTagCloud();

	}

	public void onHandleHistory(String presenterID) {
		if (presenterID != null) {

			if (presenterID.equals(DataSetsPresenter.presenterID)) {

				refreshDataSets();
				String datasetIdHash = Window.Location.getHash();
				if (datasetIdHash.toLowerCase().contains(
						dataSetIdString.toLowerCase())) {

					showDataSet(datasetIdHash.split("=")[1]);

				} else {
					addWidgetToView((Widget) dataSetPreviewWidget);
				}

			}
		}
	}

	public void onSetView(IsWidget widget, String pageName, String presenterID,
			HashMap<String, String> queryParameters) {
		/*
		 * if (presenterID.equals(DataSetsPresenter.presenterID)) { if
		 * (queryParameters.keySet().contains(dataSetIdString)) { String
		 * dataSetId = queryParameters.get(dataSetIdString);
		 * showDataSet(dataSetId); } }
		 */
	}
	public void onGoToHomepage() {
       refreshDataSets();
       addWidgetToView((Widget) dataSetPreviewWidget);

	}

}
