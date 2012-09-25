package de.bbcdaas.opendata.gwt.client.layoutviews.subviews.presenters;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.Uploader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.UploadView;
import de.bbcdaas.opendata.gwt.client.layoutviews.subviews.interfaces.IUploadView;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.AutoSuggestForm;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.DataDescriptionWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.KeyValueScreen;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.KeyValueWidget;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.SetHeaderScreen;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.UploadWidget;
import de.bbcdaas.opendata.gwt.client.services.IDataSetServiceAsync;
import de.bbcdaas.opendata.gwt.shared.DataSet;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DataSetInfo;
import de.bbcdaas.opendata.gwt.shared.DataSets;

@Presenter(view = UploadView.class)
public class UploadPresenter extends BasePresenter<IUploadView, AppEventBus> {

	public final static String presenterID = "Upload";
	String dataSetId;

	@Inject
	IDataSetServiceAsync dataServiceAsync;
	UploadWidget uploadWidget;

	Uploader uploader;
	HasWidgets contentPanel;
	SetHeaderScreen setHeaderScreen;
	DataDescriptionWidget dataDescriptionWidget;
	KeyValueScreen keyValueScreen;
	String fileUrl;
	DataSet dataset;

	@Override
	public void bind() {

		setHeaderScreen = GWT.create(SetHeaderScreen.class);
		dataServiceAsync.getTags(new AsyncCallback<HashMap<String, Integer>>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(HashMap<String, Integer> result) {
				AutoSuggestForm autoSuggestForm = new AutoSuggestForm(result
						.keySet());
				dataDescriptionWidget = new DataDescriptionWidget(
						autoSuggestForm);

			}
		});

		this.contentPanel = view.getContentPanel();
		uploadWidget = GWT.create(UploadWidget.class);
		view.setCurrentStep(1);
		contentPanel.add(uploadWidget);

		this.uploader = uploadWidget.getUploader();
		uploadWidget.getNextScreenBtn().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				char delimiter = uploadWidget.getDelimeter();
				parseFile(delimiter);

			}

		});

		setHeaderScreen.getSaveClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						loadDescriptionScreen();
					}

				});

		// Add a finish handler which will load the image once the upload
		// finishes
		uploader.addOnFinishUploadHandler(onFinishUploaderHandler);

	}

	private void parseFile(char delimiter) {
		eventBus.appLoading(false, view.getViewAsWidget());
		dataServiceAsync.parseFile(fileUrl, delimiter,
				new AsyncCallback<DataSet>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(DataSet result) {

						dataSetId = result.getId();

						dataset = result;
						eventBus.appLoading(true, view.getViewAsWidget());

						loadHeaderScreen(dataSetId);

					}
				});

	}

	protected void loadDescriptionScreen() {

		ArrayList<DataSetColumn> columns = setHeaderScreen
				.getColumnFinalHeaders();
		// DataSets.dataSets.get(dataSetId).setColumns(columns);
		dataServiceAsync.setDataSetHeader(dataSetId, columns,
				new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {

						setDescriptionScreen();

					}

					@Override
					public void onFailure(Throwable caught) {

					}

				});
	}

	// Load the image in the document and in the case of success attach it to
	// the viewer
	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		@Override
		public void onFinish(IUploader uploader) {

			if (uploader.getStatus() == Status.SUCCESS) {

				// The server sends useful information to the client by default
				UploadedInfo info = uploader.getServerInfo();

				System.out.println("File name " + info.name);
				System.out.println("File content-type " + info.ctype);
				System.out.println("File size " + info.message);

				// You can send any customized message and parse it
				System.out.println("Server message " + info.message);

				fileUrl = info.message.split("=")[1];

			}
		}
	};

	private void setDescriptionScreen() {

		dataDescriptionWidget.getPublishClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						publishDataSet();
						dataset.setName(dataDescriptionWidget.getName());

						putDatasetInfoStatic(dataset);

					}

					

				
				});

		eventBus.appLoading(false, view.getViewAsWidget());
		view.setCurrentStep(3);

		contentPanel.clear();
		contentPanel.add(dataDescriptionWidget);

		eventBus.appLoading(true, view.getViewAsWidget());

	}
private void putDatasetInfoStatic(DataSet dataset) {
						dataServiceAsync.setDatasetInfo(dataSetId, dataset, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Void result) {
								
							}
						});
						
					}
	protected void publishDataSet() {

		ArrayList<String> tags = dataDescriptionWidget.getTags();
		String name = dataDescriptionWidget.getName();
		String description = dataDescriptionWidget.getDescription();

		DataSetDescription dataSetDescription = new DataSetDescription();
		dataSetDescription.setDataSetId(dataSetId);
		dataSetDescription.setName(name);
		dataSetDescription.addTags(tags);

		dataSetDescription.setDescription(description);
		dataServiceAsync.setDescription(dataSetId, dataSetDescription,
				new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						clearFields();

					}

				});
		loadKeyValueScreen();

	}

	private void loadKeyValueScreen() {
		this.keyValueScreen = GWT.create(KeyValueScreen.class);

		keyValueScreen.getAddPropertyClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						final KeyValueWidget keyValueWidget = GWT
								.create(KeyValueWidget.class);
						keyValueWidget.getCancelBtnClickHandlers()
								.addClickHandler(new ClickHandler() {

									@Override
									public void onClick(ClickEvent event) {
										keyValueScreen
												.deleteKeyValueWidget(keyValueWidget
														.getIndex());

									}
								});
						keyValueScreen.addKeyValueWidget(keyValueWidget);
					}
				});
		contentPanel.clear();
		contentPanel.add(keyValueScreen);

		keyValueScreen.getSubmitValuesClickHandlers().addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						ArrayList<KeyValueWidget> keyValueWidgets = keyValueScreen
								.getAllIKeyValueWidgets();
						eventBus.goToHomepage();

					}
				});

	}

	protected void clearFields() {
		uploadWidget.getUploader().reset();
	}

	protected void loadHeaderScreen(String dataSetId) {
		eventBus.appLoading(false, view.getViewAsWidget());
		view.setCurrentStep(2);

		dataServiceAsync.getDataSetHeader(dataSetId,
				new AsyncCallback<ArrayList<DataSetColumn>>() {

					@Override
					public void onSuccess(ArrayList<DataSetColumn> result) {

						setHeaders(result);
						eventBus.appLoading(true, view.getViewAsWidget());
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});

	}

	private void setHeaders(ArrayList<DataSetColumn> result) {
		eventBus.appLoading(false, view.getViewAsWidget());

		contentPanel.clear();
		contentPanel.add(setHeaderScreen);

		setHeaderScreen.setColumnInitialHeaders(result);

		eventBus.appLoading(true, view.getViewAsWidget());
	}

	public void onHandleHistory(String presenterID) {
		if (presenterID != null) {
			if (presenterID.equals(presenterID)) {
				{

					contentPanel.clear();
					contentPanel.add(uploadWidget);

				}
			}

		}
	}

}
