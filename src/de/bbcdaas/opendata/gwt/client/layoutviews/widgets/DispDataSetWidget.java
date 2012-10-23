package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDispDataSetWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;

public class DispDataSetWidget extends Composite implements IDispDataSetWidget {

	private static DispDataSetWidgetUiBinder uiBinder = GWT
			.create(DispDataSetWidgetUiBinder.class);
	@UiField Label datasetNameLbl;
	@UiField Button deleteBtn;
	String datasetId;

	interface DispDataSetWidgetUiBinder extends
			UiBinder<Widget, DispDataSetWidget> {
	}

	public DispDataSetWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getDeleteBtnClickHandlers() {
	return deleteBtn;
	}

	@Override
	public void setDataset(DataSetInfo dataSetInfo) {
	datasetNameLbl.setText(dataSetInfo.getName());
	this.datasetId=dataSetInfo.getId();
		
	}

	@Override
	public String getDatasetId() {
	return datasetId;
	}
	
	

	
}
