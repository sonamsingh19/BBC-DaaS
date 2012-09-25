package de.bbcdaas.opendata.gwt.client.layoutviews;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILoginView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILogoutView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ITopView;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;

@Singleton
public class TopView extends Composite implements ITopView {

	private static TopViewUiBinder uiBinder = GWT.create(TopViewUiBinder.class);


	LoginView loginView;

	LogoutView logoutView;
	

	
	@UiField HTMLPanel loginContainer;
	@UiField Anchor homePageLink;
	
	
	
	interface TopViewUiBinder extends UiBinder<Widget, TopView> {
	}

	@Inject
	public TopView(LoginView loginView,LogoutView logoutView ) {

		this.loginView = loginView;
		this.logoutView = logoutView;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasWidgets getloginContainer() {
		return loginContainer;
	}

	@Override
	public ILoginView getLoginView() {
		return loginView;
	}

	@Override
	public ILogoutView getLogoutView() {
		
		return logoutView;
	}

	@Override
	public void clearLoginFields() {
    loginView.clearFields();
		
	}

	@Override
	public HasClickHandlers getHomepageLink() {
		return homePageLink;
	}

	



}

