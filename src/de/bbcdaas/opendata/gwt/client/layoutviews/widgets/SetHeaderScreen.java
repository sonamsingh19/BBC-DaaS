package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.ISetHeaderScreen;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;

public class SetHeaderScreen extends Composite implements ISetHeaderScreen {

	private static SetHeaderScreenUiBinder uiBinder = GWT
			.create(SetHeaderScreenUiBinder.class);
	@UiField
	FlexTable columnsTable;
	@UiField
	CheckBox isFirstRowHeaderCB;
	@UiField
	PushButton saveBtn;
	int numberofColumns;
	ArrayList<DataSetColumn> columnList;

	interface SetHeaderScreenUiBinder extends UiBinder<Widget, SetHeaderScreen> {
	}

	public SetHeaderScreen() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setColumnInitialHeaders(ArrayList<DataSetColumn> sampleValues) {
		columnList = sampleValues;
		numberofColumns = sampleValues.size();
		for (int i = 0; i < numberofColumns; i++)

		{
			ColumnTypeOptions columnTypeOptions = GWT
					.create(ColumnTypeOptions.class);
			com.google.gwt.user.client.ui.TextBox textBox = new TextBox();
			String nameString = sampleValues.get(i).getName();
			textBox.setText(nameString);

			columnsTable.setWidget(i, 0, textBox);
			columnsTable.setWidget(i, 1, columnTypeOptions);
		}

	}

	@Override
	public ArrayList<DataSetColumn> getColumnFinalHeaders() {

		for (int i = 0; i < numberofColumns; i++)

		{
			TextBox tBox = (TextBox) columnsTable.getWidget(i, 0);
			ColumnTypeOptions options = (ColumnTypeOptions) columnsTable
					.getWidget(i, 1);

			columnList.get(i).setName(tBox.getText());

			columnList.get(i).setColumnType(options.getSelectedColumnType());

		}
		return columnList;

	}

	@Override
	public Boolean isFirstRowHeader() {
		return isFirstRowHeaderCB.getValue();
	}

	@Override
	public HasClickHandlers getSaveClickHandlers() {
		return saveBtn;
	}

}
