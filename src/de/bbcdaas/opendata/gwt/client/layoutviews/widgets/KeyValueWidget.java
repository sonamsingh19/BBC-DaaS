package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IKeyValueWidget;

public class KeyValueWidget extends Composite implements IKeyValueWidget {

	private static KeyValueWidgetUiBinder uiBinder = GWT
			.create(KeyValueWidgetUiBinder.class);
	@UiField
	TextBox keyTB;
	@UiField
	TextBox valueTB;
	@UiField
	Button cancelBtn;
	int index;

	interface KeyValueWidgetUiBinder extends UiBinder<Widget, KeyValueWidget> {
	}

	public KeyValueWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public String getKey() {
		return keyTB.getText();
	}

	@Override
	public String getValue() {
		return valueTB.getText();
	}

	@Override
	public HasClickHandlers getCancelBtnClickHandlers() {
		return cancelBtn;
	}

	@Override
	public void setIndex(int index) {

		this.index = index;
	}

	@Override
	public int getIndex() {
		return index;
	}

}
