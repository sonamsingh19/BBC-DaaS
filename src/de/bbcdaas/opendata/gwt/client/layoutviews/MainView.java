package de.bbcdaas.opendata.gwt.client.layoutviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IMainView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.ContentPaneView;

@Singleton
public class MainView extends Composite implements IMainView {

	ContentPaneView contentPaneView;
	private static MainViewUiBinder uiBinder = GWT
			.create(MainViewUiBinder.class);
	@UiField
	HTMLPanel mainPanel;

	interface MainViewUiBinder extends UiBinder<Widget, MainView> {
	}

	@Inject
	public MainView(ContentPaneView contentPaneView) {
		this.contentPaneView = contentPaneView;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setWidget(IsWidget widget) {
		mainPanel.clear();
		mainPanel.add(widget);
	}

	@Override
	public ContentPaneView getContentPane() {
		return contentPaneView;
	}

}
