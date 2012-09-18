package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFilterScreen;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DownloadFormats;

public class FilterScreen extends Composite implements IFilterScreen  {

	private static FilterScreenUiBinder uiBinder = GWT
			.create(FilterScreenUiBinder.class);
	@UiField
	DisclosurePanel filters;
	@UiField
	PushButton addFilter;
	@UiField
	VerticalPanel filterPanel;
	@UiField
	FlexTable columnDetailsTable;
	@UiField
	Label dataSetIdLbl;
	@UiField
	Label datasetNameLbl;
	@UiField
	ListBox datasetdownLoadtype;
	@UiField
	Anchor downloadLink;
	@UiField
	TextArea UserQueryTB;
	@UiField TextArea descriptionTB;
	HashMap<Integer, FilterWidget> filterWidgets;

	interface FilterScreenUiBinder extends UiBinder<Widget, FilterScreen> {
	}

	public FilterScreen() {
		initWidget(uiBinder.createAndBindUi(this));
		filterWidgets = new HashMap<Integer, FilterWidget>();
		attachDownloadType();
		UserQueryTB.getElement().getStyle().setProperty("maxWidth", "200px");
		UserQueryTB.getElement().getStyle().setProperty("maxHeight", "150px");
	}

	private void attachDownloadType() {
		for (DownloadFormats downloadFormat : DownloadFormats.values()) {
			datasetdownLoadtype.addItem(downloadFormat.toString());
		}

		datasetdownLoadtype.setSelectedIndex(0);

	}

	@Override
	public HasClickHandlers getAddFilterClickHandlers() {
		return addFilter;
	}

	@Override
	public void DeleteFilter(int index) {
		IsWidget filterWidget = filterWidgets.get(index);
		filterPanel.remove(filterWidget);
		filterWidgets.remove(index);

	}

	@Override
	public void setColumns(ArrayList<DataSetColumn> columns) {
		int row = 0;
		columnDetailsTable.setWidget(row, 0, new Label("Columns"));
		columnDetailsTable.setWidget(row, 1, new Label("ID"));
		for (DataSetColumn dataSetColumn : columns) {
			row++;
			columnDetailsTable.setWidget(row, 0,
					new Label(dataSetColumn.getName()));
			columnDetailsTable.setWidget(row, 1,
					new Label(dataSetColumn.getId()));

		}

	}

	@Override
	public void setDataSetName(String dataSetName) {
		datasetNameLbl.setText(dataSetName);

	}

	@Override
	public void AddNewFilter(FilterWidget filterWidget) {
		filterPanel.add(filterWidget);

		int index = filterWidget.getIndex();
		filterWidgets.put(index, filterWidget);

		for (Integer key : filterWidgets.keySet()) {
			System.out.println(key);
		}

	}

	@Override
	public void setDataSetId(String dataSetId, DownloadFormats format) {
		dataSetIdLbl.setText(dataSetId);
		int selectedIndex = datasetdownLoadtype.getSelectedIndex();
		String moduleName=GWT.getModuleName();
		downloadLink.setHref(moduleName+"/services/download?DatasetId="
				+ dataSetId
				+ "&&"
				+ "format="
				+ DownloadFormats.valueOf(datasetdownLoadtype
						.getItemText(selectedIndex)));

	}

	@Override
	public Anchor getDownloadlink() {
		return downloadLink;
	}

	@Override
	public DownloadFormats getSelectedDownloadFormat() {
		int selectedDownloadIndex = datasetdownLoadtype.getSelectedIndex();
		return DownloadFormats.valueOf(datasetdownLoadtype
				.getItemText(selectedDownloadIndex));
	}

	@Override
	public ListBox getDownloadTypeLB() {
		return datasetdownLoadtype;
	}

	@Override
	public void setMetadata(DataSetDescription description) {
		descriptionTB.setText(description.getDescription());
		
	}

}
