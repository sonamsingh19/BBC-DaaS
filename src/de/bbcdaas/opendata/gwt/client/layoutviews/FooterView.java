package de.bbcdaas.opendata.gwt.client.layoutviews;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.interfaces.IFooterView;

public class FooterView extends Composite implements IFooterView {

	private static FooterViewUiBinder uiBinder = GWT
			.create(FooterViewUiBinder.class);

	interface FooterViewUiBinder extends UiBinder<Widget, FooterView> {
	}

	public FooterView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
