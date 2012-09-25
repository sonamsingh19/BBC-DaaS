package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IColumnTypeOptionsWidget;
import de.bbcdaas.opendata.gwt.shared.ColumnType;

public class ColumnTypeOptions extends Composite implements
		IColumnTypeOptionsWidget {

	ColumnType[] columns;
	private static ColumnTypeOptionsUiBinder uiBinder = GWT
			.create(ColumnTypeOptionsUiBinder.class);
	@UiField
	ListBox columnTypeList;

	interface ColumnTypeOptionsUiBinder extends
			UiBinder<Widget, ColumnTypeOptions> {
	}

	public ColumnTypeOptions() {
		initWidget(uiBinder.createAndBindUi(this));
		columns = ColumnType.values();
		for (int i = 0; i <columns.length; i++) {
			columnTypeList.addItem(columns[i].toString());
		}

	}

	@Override
	public ColumnType getSelectedColumnType() {

		return columns[columnTypeList.getSelectedIndex()];
	}

}
