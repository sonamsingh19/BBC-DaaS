package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MyAccountWidget extends Composite  {

	private static MyAccountWidgetUiBinder uiBinder = GWT
			.create(MyAccountWidgetUiBinder.class);
	@UiField TextBox currentEmail;

	interface MyAccountWidgetUiBinder extends UiBinder<Widget, MyAccountWidget> {
	}

	public MyAccountWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	public MyAccountWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}



}
