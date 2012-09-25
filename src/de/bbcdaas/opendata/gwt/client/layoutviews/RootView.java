package de.bbcdaas.opendata.gwt.client.layoutviews;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IRootView;

public class RootView extends Composite implements IRootView {

	private static RootViewUiBinder uiBinder = GWT
			.create(RootViewUiBinder.class);


	
	@UiField(provided = true)
	TopView topView;
	

	
	@UiField(provided = true)
	MenuView menuView;
	

    @Inject
	@UiField(provided = true)
	MainView mainView;
	


	@UiField(provided = true)
	FooterView footerView;


	
	interface RootViewUiBinder extends UiBinder<Widget, RootView> {
	}


	@Inject
	public RootView(MainView mainView, MenuView menuView, FooterView footerView, TopView topView) {

		this.mainView = mainView;
		this.menuView = menuView;
	    this.footerView = footerView;
       
		this.topView= topView;
		initWidget(uiBinder.createAndBindUi(this));
	}


	

	

	
	
}
