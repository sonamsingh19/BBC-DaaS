package de.bbcdaas.opendata.gwt.client.layoutviews.subviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IUploadView;

@Singleton
public class UploadView extends Composite implements IUploadView {

	private static UploadViewUiBinder uiBinder = GWT
			.create(UploadViewUiBinder.class);
	@UiField HTMLPanel contentPanel;
	interface UploadViewUiBinder extends UiBinder<Widget, UploadView> {
	}

	public UploadView() {
		initWidget(uiBinder.createAndBindUi(this));
		
	setFlowWidget();
	}

	private void setFlowWidget() {
	/*	<l:FlowWidget ui:field="flowWidget" height="25px"/>
		@UiField FlowWidget flowWidget;
	
		flowWidget.setNumberOfSteps(3);
	flowWidget.setStepText(1,"Upload");
	flowWidget.setStepText(2,"Save");
	flowWidget.setStepText(3,"Describe");*/
		
	}

	

	@Override
	public ViewNames getViewName() {
		return ViewNames.UploadView;
	}

	@Override
	public void setCurrentStep(int step) {
	
		
	}

	@Override
	public HasWidgets getContentPanel() {
		return contentPanel;
	}

	@Override
	public Widget getViewAsWidget() {
		return this;
	}

	


}
