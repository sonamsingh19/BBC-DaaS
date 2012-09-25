package de.bbcdaas.opendata.gwt.client.layoutviews.subviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IContentPane;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.NavigationPane;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.INavigationPane;

@Singleton
public class ContentPaneView extends Composite implements IContentPane {

	private static ContentPaneViewUiBinder uiBinder = GWT
			.create(ContentPaneViewUiBinder.class);

	
	@UiField
	SimplePanel containerPanel;
	@UiField
	NavigationPane navigationPane;
	
	
	@Inject UploadView uploadView;
	@Inject DataSetsView dataSetsView;

	interface ContentPaneViewUiBinder extends UiBinder<Widget, ContentPaneView> {
	}


	public ContentPaneView() {
		
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	@Override
	public ViewNames getViewName() {
		return ViewNames.ContentPaneView;
	}

	@Override
	public void setContainerPanel(IsWidget widget) {
		containerPanel.clear();
		containerPanel.add(widget);

	}

	@Override
	public INavigationPane getNavigationPane() {
		return navigationPane;
	}

	@Override
	public DataSetsView getDataSetsView() {
		return dataSetsView;
	}

	@Override
	public UploadView getUploadView() {

		return uploadView;
	}

}
