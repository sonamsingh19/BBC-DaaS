package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java_cup.internal_error;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyDatasets;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;

public class MyDatasetsWidget extends Composite implements IMyDatasets {

	int i;
	private static MyDatasetsWidgetUiBinder uiBinder = GWT
			.create(MyDatasetsWidgetUiBinder.class);
	@UiField
	FlexTable datasetsTable;

	interface MyDatasetsWidgetUiBinder extends
			UiBinder<Widget, MyDatasetsWidget> {
	}

	public MyDatasetsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		i = 0;
	}

	@Override
	public void addDatasets(DataSetInfo dataSetInfo) {

		Label label = new Label(dataSetInfo.getName());
		Label downloadLabel = new Label(dataSetInfo.getDatePosted().toString());

		datasetsTable.setWidget(i, 1, label);
		datasetsTable.setWidget(i, 2, downloadLabel);
		i++;
	}

	@Override
	public void clearDatasets() {
		datasetsTable.clear();
		
	}

}
