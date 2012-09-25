package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IFilterWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;

public class FilterWidget extends Composite implements IFilterWidget {

	private static FilterWidgetUiBinder uiBinder = GWT
			.create(FilterWidgetUiBinder.class);
	ArrayList<DataSetColumn> columns;

	@UiField
	ListBox columnsLB;
	@UiField
	ImageButton applyFilter;
	@UiField
	ImageButton cancelFilter;
	@UiField
	ListBox operationsLB;
	@UiField
	TextBox filterValue;
	int index;

	interface FilterWidgetUiBinder extends UiBinder<Widget, FilterWidget> {
	}

	public FilterWidget() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@Override
	public void setColumns(ArrayList<DataSetColumn> columns) {
		this.columns = columns;
		for (DataSetColumn dataSetColumn : columns) {
			columnsLB.addItem(dataSetColumn.getName());
		}

	}

	@Override
	public HasClickHandlers getApplyFilterClickHandlers() {
		return applyFilter;
	}

	@Override
	public DataSetColumn getSelectedColumn() {
		return columns.get(columnsLB.getSelectedIndex());

	}

	@Override
	public void setOperations(ArrayList<String> operations) {
		operationsLB.clear();
		for (String operation : operations) {
			operationsLB.addItem(operation);
		}
	}

	@Override
	public HasChangeHandlers getColumnSelectedHandlers() {
		return columnsLB;
	}

	@Override
	public HasClickHandlers getCancelClickHandlers() {
		return cancelFilter;
	}

	@Override
	public void setIndex(int index) {
		this.index = index;

	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getSelectedOperation() {
		return operationsLB.getValue(operationsLB.getSelectedIndex());
	}

	@Override
	public String getOperandValue() {
		return filterValue.getText();
	}

}
