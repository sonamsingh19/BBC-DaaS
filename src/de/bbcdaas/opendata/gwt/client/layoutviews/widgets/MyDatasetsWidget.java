package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyDatasets;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public class MyDatasetsWidget extends Composite implements IMyDatasets {

	private static MyDatasetsWidgetUiBinder uiBinder = GWT
			.create(MyDatasetsWidgetUiBinder.class);
	@UiField
	VerticalPanel datasetsPanel;

	HashMap<String, DispDataSetWidget> dispDataSetWidgets;

	interface MyDatasetsWidgetUiBinder extends
			UiBinder<Widget, MyDatasetsWidget> {
	}

	public MyDatasetsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		dispDataSetWidgets=new HashMap<String, DispDataSetWidget>();
	}

	@Override
	public DispDataSetWidget addDatasets(DataSetInfo dataSetInfo) {

		DispDataSetWidget dispdatasetWidget = GWT
				.create(DispDataSetWidget.class);
		dispdatasetWidget.setDataset(dataSetInfo);
		datasetsPanel.add(dispdatasetWidget);
		dispDataSetWidgets.put(dispdatasetWidget.getDatasetId(),
				dispdatasetWidget);
		return dispdatasetWidget;

	}

	@Override
	public void clearDatasets() {
		datasetsPanel.clear();

	}

	@Override
	public void deleteDataset(String datasetId) {
		DispDataSetWidget deletedDispDatasetWidget = dispDataSetWidgets
				.remove(datasetId);
		datasetsPanel.remove(deletedDispDatasetWidget);

	}

}
