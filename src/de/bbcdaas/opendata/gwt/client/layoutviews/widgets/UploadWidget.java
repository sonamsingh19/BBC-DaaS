package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import gwtupload.client.Uploader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IUploadWidget;
import com.google.gwt.user.client.ui.PushButton;

public class UploadWidget extends Composite implements IUploadWidget {

	private static UploadWidgetUiBinder uiBinder = GWT
			.create(UploadWidgetUiBinder.class);
	@UiField
	Uploader upload;
	@UiField
	RadioButton customRB;
	@UiField
	TextBox delimiterTB;
	@UiField
	RadioButton tabRB;
	@UiField
	RadioButton commaRB;
	@UiField
	PushButton nextBtn;
	boolean customRBSelected;

	interface UploadWidgetUiBinder extends UiBinder<Widget, UploadWidget> {
	}

	public UploadWidget() {

		initWidget(uiBinder.createAndBindUi(this));
		delimiterTB.setEnabled(false);
		customRB.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				boolean value = customRB.getValue();
				if (value) {
					delimiterTB.setEnabled(true);
					customRBSelected = true;
				} else {
					delimiterTB.setEnabled(false);
				}
			}
		});
		tabRB.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				customRBSelected = false;
				delimiterTB.setEnabled(false);
			}
		});
		commaRB.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				customRBSelected = false;
				delimiterTB.setEnabled(false);
			}
		});
	}

	@Override
	public Uploader getUploader() {
		return upload;
	}

	@Override
	public char getDelimeter() {

		char delimiter = ',';
		if (commaRB.getValue())
			delimiter = ',';
		else if (tabRB.getValue())
			delimiter = '\t';
		else if (customRB.getValue())
			delimiter = delimiterTB.getText().trim().charAt(0);
		return delimiter;
	}

	@Override
	public HasClickHandlers getNextScreenBtn() {
		return nextBtn;
	}

	@Override
	public void setNextBtnEnabled(Boolean enabled) {
		 nextBtn.setEnabled(enabled);
	}

}
