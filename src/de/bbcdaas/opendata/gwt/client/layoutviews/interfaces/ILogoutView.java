package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;



import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.MyProfileView;

public interface ILogoutView {

	HasClickHandlers getLogoutClickHandlers();
	HasText getUserNameBox();
	HasClickHandlers getAccountClickHandlers();
	MyProfileView getMyProfileView();
	
}
