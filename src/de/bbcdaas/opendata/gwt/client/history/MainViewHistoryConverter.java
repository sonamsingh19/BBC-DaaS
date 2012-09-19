package de.bbcdaas.opendata.gwt.client.history;


import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.History.HistoryConverterType;
import com.mvp4g.client.history.HistoryConverter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.Helpers;

@History(type = HistoryConverterType.DEFAULT)
public class MainViewHistoryConverter implements HistoryConverter<AppEventBus> {

	
	@Override
	public void convertFromToken(String historyName, String param,
			AppEventBus eventBus) {
		
 String id=  Helpers.getPresenterID(param);
  
		eventBus.handleHistory(id);
 
		
	}

	@Override
	public boolean isCrawlable() {

		return false;
	}

	public String convertToToken(String eventName, IsWidget body,
			String pageName, String presenterID) {
		return pageName;
	}

	public String onSetMainBody(IsWidget body, String pageName,String presenterID) {
		return "view="+pageName+"&&ID="+presenterID;

	}
	
	
}
