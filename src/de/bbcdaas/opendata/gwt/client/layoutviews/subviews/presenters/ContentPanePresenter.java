package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.ContentPaneView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.DataSetsView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.UploadView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IContentPane;

@Presenter(view = ContentPaneView.class)
public class ContentPanePresenter extends
		BasePresenter<IContentPane, AppEventBus> {

	public final static String presenterId = "ContentPane";

	DataSetsView dataSetsView;

	UploadView uploadView;

	public ContentPanePresenter() {

	}

	public void onSetView(IsWidget widget, String pageName, String presenterID,
			HashMap<String, String> queryString) {
		view.setContainerPanel(widget);
	}

	public void onStart() {
		this.dataSetsView = view.getDataSetsView();
		this.uploadView = view.getUploadView();
		HashMap<String, String> queryStrings = new HashMap<String, String>();
		String datasetIdHash = Window.Location.getHash();
		if (!datasetIdHash.isEmpty()) {
			String queryString = datasetIdHash.substring(datasetIdHash
					.indexOf("?") + 1);
			String[] queryValues = queryString.split("&&");
			if (queryValues.length != 0) {
				for (String queryValue : queryValues) {
					String[] queryKeyValue = queryValue.split("=");
					queryStrings.put(queryKeyValue[0], queryKeyValue[1]);
				}
			}
		}
		eventBus.setView(dataSetsView, dataSetsView.getViewName().toString(),
				DataSetsPresenter.presenterID, queryStrings);
	}

	@Override
	public void bind() {

		view.getNavigationPane().getUploadHandlers()
				.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						uploadClicked();

					}
				});
	}

	protected void uploadClicked() {
		eventBus.setView(uploadView, uploadView.getViewName().toString(),
				UploadPresenter.presenterID, new HashMap<String, String>());
	}

	public void onHandleHistory(String presenterID) {
		if (presenterID != null) {

			if (presenterID.equals(DataSetsPresenter.presenterID)) {
				eventBus.setView(dataSetsView, dataSetsView.getViewName()
						.toString(), DataSetsPresenter.presenterID,
						new HashMap<String, String>());
			} else

			if (presenterID.equals(UploadPresenter.presenterID)) {
				eventBus.setView(uploadView, uploadView.getViewName()
						.toString(), UploadPresenter.presenterID,
						new HashMap<String, String>());

			} else {
				eventBus.setView(dataSetsView, dataSetsView.getViewName()
						.toString(), DataSetsPresenter.presenterID,
						new HashMap<String, String>());

			}

		}
	}

	public void onGoToHomepage() {
		eventBus.setView(dataSetsView, dataSetsView.getViewName().toString(),
				DataSetsPresenter.presenterID, new HashMap<String, String>());

		
	}

}
