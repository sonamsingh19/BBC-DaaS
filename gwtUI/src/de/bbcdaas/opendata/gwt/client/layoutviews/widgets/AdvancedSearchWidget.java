package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Widget;

public class AdvancedSearchWidget extends DialogBox implements HasText {

	private static AdvancedSearchWidgetUiBinder uiBinder = GWT
			.create(AdvancedSearchWidgetUiBinder.class);
	@UiField ImageButton close;
	@UiField IntegerBox minDownloads;

	interface AdvancedSearchWidgetUiBinder extends
			UiBinder<Widget, AdvancedSearchWidget> {
	}

	public AdvancedSearchWidget() {
		setWidget(uiBinder.createAndBindUi(this));
	}

	public AdvancedSearchWidget(String firstName) {
		setWidget(uiBinder.createAndBindUi(this));
		close.setText(firstName);
	}

	@Override
	public void setText(String text) {
		close.setText(text);
	}

	@Override
	public String getText() {
		return close.getText();
	}

	@UiHandler("close")
	void onCloseClick(ClickEvent event) {
		this.hide();
	}
}
