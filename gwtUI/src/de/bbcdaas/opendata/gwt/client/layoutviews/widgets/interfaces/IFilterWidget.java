package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;

import de.bbcdaas.opendata.gwt.shared.DataSetColumn;

public interface IFilterWidget {
	void setColumns(ArrayList<DataSetColumn> columns);

	HasClickHandlers getApplyFilterClickHandlers();

	DataSetColumn getSelectedColumn();

	void setOperations(ArrayList<String> operations);

	HasChangeHandlers getColumnSelectedHandlers();

	HasClickHandlers getCancelClickHandlers();

	String getSelectedOperation();

	String getOperandValue();

	void setIndex(int index);

	int getIndex();
}
