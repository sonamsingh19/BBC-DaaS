package de.bbcdaas.opendata.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.Mvp4gModule;

public class GwtUI implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		Mvp4gModule module = (Mvp4gModule)GWT.create( Mvp4gModule.class );
		module.createAndStartModule();
		RootPanel.get().add( (Widget)module.getStartView() );
		
	}

}
