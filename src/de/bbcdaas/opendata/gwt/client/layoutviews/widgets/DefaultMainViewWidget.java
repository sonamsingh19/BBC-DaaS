package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DefaultMainViewWidget extends Composite  {

	private static DefaultMainViewWidgetUiBinder uiBinder = GWT
			.create(DefaultMainViewWidgetUiBinder.class);

	interface DefaultMainViewWidgetUiBinder extends
			UiBinder<Widget, DefaultMainViewWidget> {
	}

	public DefaultMainViewWidget() {
		
		
		initWidget(uiBinder.createAndBindUi(this));
	}

	public DefaultMainViewWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}


}
