package de.bbcdaas.opendata.gwt.client.layout.presenters;

import com.google.gwt.user.client.ui.Widget;
import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.AjaxLoaderView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IAjaxLoaderView;

@Presenter(view = AjaxLoaderView.class)
public class AjaxLoaderPresenter extends
		BasePresenter<IAjaxLoaderView, AppEventBus> {

	public void onAppLoading(boolean isComplete,Widget relativeWidget) {

		if (isComplete)

			view.stopProcessing();

		else

			view.startProcessing(relativeWidget);

	}
}