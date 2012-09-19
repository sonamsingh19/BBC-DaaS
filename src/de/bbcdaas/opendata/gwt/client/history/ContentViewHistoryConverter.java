package de.bbcdaas.opendata.gwt.client.history;

import java.util.HashMap;

import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.History;
import com.mvp4g.client.annotation.History.HistoryConverterType;
import com.mvp4g.client.history.HistoryConverter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.Helpers;

@History(type = HistoryConverterType.DEFAULT)
public class ContentViewHistoryConverter implements
		HistoryConverter<AppEventBus> {

	@Override
	public void convertFromToken(String historyName, String param,
			AppEventBus eventBus) {

		try {
			String id = Helpers.getPresenterID(param);
			eventBus.handleHistory(id);

		} catch (NullPointerException e) {
			
			System.out.println("error:failed to get presenter id ,Querystring : "+param);
		}

	}

	@Override
	public boolean isCrawlable() {
		// TODO Auto-generated method stub
		return false;
	}

	public String onSetView(IsWidget body, String pageName, String presenterID,
			HashMap<String, String> queryParameters) {
		String queryString = "view=" + pageName + "&&ID=" + presenterID;
		for (String key : queryParameters.keySet()) {
			queryString = queryString + "&&" + key + "="
					+ queryParameters.get(key);
		}
		return queryString;

	}
	
	

	
}