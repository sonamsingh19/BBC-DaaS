package de.bbcdaas.opendata.gwt.client.layoutviews.subviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IDataSetsView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DataSetsPreviewWidget;

@Singleton
public class DataSetsView extends Composite implements IDataSetsView {

	private static DataSetsViewUiBinder uiBinder = GWT
			.create(DataSetsViewUiBinder.class);
	@UiField HTMLPanel contentPanel;


	@Inject
	DataSetsPreviewWidget dataSetsPreviewWidget;
	interface DataSetsViewUiBinder extends UiBinder<Widget, DataSetsView> {
	}

	public DataSetsView() {

		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@Override
	public ViewNames getViewName() {
		return ViewNames.DataSetsView;
	}

	

	@Override
	public Widget getViewAsWidget() {
	return this;
	}


	@Override
	public DataSetsPreviewWidget getDataSetsPreviewWidget() {
	return	dataSetsPreviewWidget;
	}


	@Override
	public HasWidgets getContentPanel() {
		return contentPanel;
	}

}
