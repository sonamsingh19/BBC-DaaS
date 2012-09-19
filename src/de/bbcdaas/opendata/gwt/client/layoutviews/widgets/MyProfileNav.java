package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyProfileNav;

public class MyProfileNav extends Composite implements IMyProfileNav  {

	private static MyProfileNavUiBinder uiBinder = GWT
			.create(MyProfileNavUiBinder.class);
	@UiField Button myAccountBtn;
	@UiField Button myDatasetsBtn;

	interface MyProfileNavUiBinder extends UiBinder<Widget, MyProfileNav> {
	}

	public MyProfileNav() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	

	@Override
	public HasClickHandlers getMyDataSetsClickHandlers() {
		return myDatasetsBtn;
	}


	@Override
	public HasClickHandlers getMyAccountClickHandlers() {
		return myAccountBtn;
	}

	

}
