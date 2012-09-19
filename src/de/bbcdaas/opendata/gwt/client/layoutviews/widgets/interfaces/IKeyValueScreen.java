package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.KeyValueWidget;

public interface IKeyValueScreen {

	HasClickHandlers getSubmitValuesClickHandlers();

	HasClickHandlers getAddPropertyClickHandlers();

	int addKeyValueWidget(KeyValueWidget keyValueWidget);

	ArrayList<KeyValueWidget> getAllIKeyValueWidgets();

}
