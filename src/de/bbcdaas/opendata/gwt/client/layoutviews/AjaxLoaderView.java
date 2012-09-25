package de.bbcdaas.opendata.gwt.client.layoutviews;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IAjaxLoaderView;

public class AjaxLoaderView extends PopupPanel implements IAjaxLoaderView {

	private static AjaxLoaderViewUiBinder uiBinder = GWT
			.create(AjaxLoaderViewUiBinder.class);


	interface AjaxLoaderViewUiBinder extends UiBinder<Widget, AjaxLoaderView> {
	}

	public AjaxLoaderView() {
		setWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void stopProcessing() {
		hide();

	}

	@Override
	public void startProcessing(Widget relativeWidget) {
		int left=relativeWidget.getAbsoluteLeft()+(relativeWidget.getOffsetWidth()/2);
		int top=relativeWidget.getAbsoluteTop()+(relativeWidget.getOffsetHeight()/2);
		setPopupPosition(left, top);
//		setGlassEnabled(true);
	show();
       
		

	}

}
