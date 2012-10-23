package de.bbcdaas.opendata.gwt.client.layoutviews.subviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.ViewNames;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IMyProfileView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.MyProfileNav;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyProfileNav;

@Singleton
public class MyProfileView extends Composite implements IMyProfileView {
	private static MyProfileViewUiBinder uiBinder = GWT
			.create(MyProfileViewUiBinder.class);
	@UiField HTMLPanel containerPanel;
	@UiField MyProfileNav myProfileNav;

	interface MyProfileViewUiBinder extends UiBinder<Widget, MyProfileView> {
	}

	public MyProfileView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@Override
	public ViewNames getViewName() {
		return ViewNames.MyProfileView;
	}

	@Override
	public IMyProfileNav getIMyProfileNav() {
		return myProfileNav;
	}

	@Override
	public HasWidgets getContainerPanel() {
		return containerPanel;
	}



	

}
