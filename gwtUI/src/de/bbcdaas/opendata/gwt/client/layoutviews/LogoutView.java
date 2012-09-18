package de.bbcdaas.opendata.gwt.client.layoutviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.ILogoutView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.MyProfileView;

@Singleton
public class LogoutView extends Composite implements ILogoutView {

	private static LogoutViewUiBinder uiBinder = GWT
			.create(LogoutViewUiBinder.class);
	@UiField
	Button logout;
	@UiField
	Label name;
	@UiField
	Anchor account;

	MyProfileView myProfileView;

	interface LogoutViewUiBinder extends UiBinder<Widget, LogoutView> {
	}

	@Inject
	public LogoutView(MyProfileView myProfileView) {
		this.myProfileView = myProfileView;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public HasClickHandlers getLogoutClickHandlers() {

		return logout;
	}

	@Override
	public HasText getUserNameBox() {

		return name;
	}

	@Override
	public HasClickHandlers getAccountClickHandlers() {
		return account;
	}

	@Override
	public MyProfileView getMyProfileView() {
		return myProfileView;
	}

}
