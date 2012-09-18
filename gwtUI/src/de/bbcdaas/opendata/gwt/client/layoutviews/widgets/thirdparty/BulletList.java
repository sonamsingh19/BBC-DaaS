package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

public class BulletList extends ComplexPanel {
	public BulletList() {
		setElement(DOM.createElement("UL"));
	}

	@Override
	public void add(Widget w) {
		super.add(w, getElement());
	}

	public void insert(Widget w, int beforeIndex) {
		super.insert(w, getElement(), beforeIndex, true);
	}
}

