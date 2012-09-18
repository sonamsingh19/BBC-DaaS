package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java_cup.internal_error;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IKeyValueScreen;
import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IKeyValueWidget;

public class KeyValueScreen extends Composite implements IKeyValueScreen {

	private static KeyValueScreenUiBinder uiBinder = GWT
			.create(KeyValueScreenUiBinder.class);
	@UiField
	VerticalPanel keyValuePanel;
	@UiField
	Button submitBtn;
	@UiField
	Button addPropertyBtn;
	HashMap<Integer, KeyValueWidget> keyValueWidgets;

	interface KeyValueScreenUiBinder extends UiBinder<Widget, KeyValueScreen> {
	}

	public KeyValueScreen() {
		initWidget(uiBinder.createAndBindUi(this));
		keyValueWidgets = new HashMap<Integer, KeyValueWidget>();
	}

	@Override
	public HasClickHandlers getSubmitValuesClickHandlers() {
		return submitBtn;
	}

	@Override
	public HasClickHandlers getAddPropertyClickHandlers() {
		return addPropertyBtn;
	}

	@Override
	public int addKeyValueWidget(final KeyValueWidget keyValueWidget) {
		keyValueWidget.setIndex(keyValueWidgets.size() - 1);
		keyValuePanel.add(keyValueWidget);
		keyValueWidgets.put(keyValueWidget.getIndex(), keyValueWidget);

		return keyValueWidgets.size();
	}

	@Override
	public ArrayList<KeyValueWidget> getAllIKeyValueWidgets() {

		ArrayList<KeyValueWidget> widgets=new ArrayList<KeyValueWidget>();
		for (KeyValueWidget KeyValueWidget : keyValueWidgets.values()) {
              widgets.add(KeyValueWidget);
		}
		return widgets;
		
	}

	public void deleteKeyValueWidget(int index) {
		KeyValueWidget widgetTobeDel = keyValueWidgets.remove(index);
		keyValuePanel.remove(widgetTobeDel);
	}

}
