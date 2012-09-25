package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SignupCompleted extends Composite  {

	private static SignupCompletedUiBinder uiBinder = GWT
			.create(SignupCompletedUiBinder.class);

	interface SignupCompletedUiBinder extends UiBinder<Widget, SignupCompleted> {
	}

	public SignupCompleted() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	public SignupCompleted(String code) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}






}
