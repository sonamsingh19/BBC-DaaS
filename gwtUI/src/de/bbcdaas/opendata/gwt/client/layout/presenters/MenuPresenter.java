package de.bbcdaas.opendata.gwt.client.layout.presenters;


import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;

import de.bbcdaas.opendata.gwt.client.AppEventBus;
import de.bbcdaas.opendata.gwt.client.layoutviews.MenuView;
import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IMenuView;

@Presenter(view = MenuView.class)
public class MenuPresenter extends BasePresenter<IMenuView, AppEventBus> {

}
