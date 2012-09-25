package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ListBox;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.FilterWidget;
import de.bbcdaas.opendata.gwt.shared.DataSetColumn;
import de.bbcdaas.opendata.gwt.shared.DataSetDescription;
import de.bbcdaas.opendata.gwt.shared.DownloadFormats;

public interface IFilterScreen {

	void AddNewFilter(FilterWidget filterWidget);

	HasClickHandlers getAddFilterClickHandlers();

	void DeleteFilter(int index);

	void setColumns(ArrayList<DataSetColumn> columns);

	void setDataSetId(String dataSetId, DownloadFormats format);

	Anchor getDownloadlink();

	void setDataSetName(String dataSetName);


	DownloadFormats getSelectedDownloadFormat();
	
	ListBox getDownloadTypeLB();
	
	void setMetadata(DataSetDescription description);

}
