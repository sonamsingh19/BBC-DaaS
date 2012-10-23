package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.MyProfileView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IMyProfileView;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DispDataSetWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.MyAccountWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.MyDatasetsWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IMyProfileNav;
import de.bbcdaas.opendata.gwt.client.services.IDataSetServiceAsync;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.SortingOptions;
import de.bbcdaas.opendata.gwt.shared.beans.UserDetails;

@Presenter(view = MyProfileView.class)
public class MyProfilePresenter extends
		BasePresenter<IMyProfileView, AppEventBus> {

	public final static String presenterID = "MyProfile";
	HasWidgets containerPanel;
	MyDatasetsWidget myDatasetsWidget;
	MyAccountWidget myAccountWidget;
	@Inject
	IDataSetServiceAsync dataServiceAsync;

	@Override
	public void bind() {
		this.containerPanel = view.getContainerPanel();
		this.myDatasetsWidget = GWT.create(MyDatasetsWidget.class);
		this.myAccountWidget = GWT.create(MyAccountWidget.class);
		IMyProfileNav myProfileNav = view.getIMyProfileNav();

		myProfileNav.getMyAccountClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						showMyAccount();

					}
				});

		myProfileNav.getMyDataSetsClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						showMyDatasets();

					}
				});

		showMyDatasets();
	}

	protected void showMyDatasets() {

		containerPanel.clear();

		containerPanel.add(myDatasetsWidget);
        eventBus.appLoading(false, myDatasetsWidget);
		dataServiceAsync.getDataSets(0, 10, SortingOptions.DOWNLOADS,
				new AsyncCallback<ArrayList<DataSetInfo>>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(ArrayList<DataSetInfo> result) {
						myDatasetsWidget.clearDatasets();
						for (DataSetInfo dataSetInfo : result) {

							final DispDataSetWidget dispDataSetWidget = myDatasetsWidget
									.addDatasets(dataSetInfo);
							dispDataSetWidget.getDeleteBtnClickHandlers()
									.addClickHandler(new ClickHandler() {

										@Override
										public void onClick(ClickEvent event) {
											String datasetId=dispDataSetWidget.getDatasetId();
											myDatasetsWidget.deleteDataset(datasetId);
											dataServiceAsync.DeleteDataset(datasetId, new AsyncCallback<Void>() {

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated method stub
													
												}

												@Override
												public void onSuccess(
														Void result) {

											        eventBus.appLoading(true,myDatasetsWidget);
												}
											});
										}
									});

						}

					}
				});

	}

	protected void showMyAccount() {
		containerPanel.clear();

		containerPanel.add(myAccountWidget);

	}

	public void onUserLoggedin(UserDetails user) {
		showMyDatasets();
	}
	public void onRefreshDatasets(){
		showMyDatasets();

	}

}
