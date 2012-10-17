package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDispDataSetWidget;

public class DispDataSetWidget extends Composite implements IDispDataSetWidget {

	private static DispDataSetWidgetUiBinder uiBinder = GWT
			.create(DispDataSetWidgetUiBinder.class);

	interface DispDataSetWidgetUiBinder extends
			UiBinder<Widget, DispDataSetWidget> {
	}

	public DispDataSetWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
}
