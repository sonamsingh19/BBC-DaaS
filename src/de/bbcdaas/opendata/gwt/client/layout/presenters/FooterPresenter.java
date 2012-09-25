package de.bbcdaas.opendata.gwt.client.layout.presenters;


import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.FooterView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IFooterView;

@Presenter(view = FooterView.class)
public class FooterPresenter extends BasePresenter<IFooterView, AppEventBus> {

}
