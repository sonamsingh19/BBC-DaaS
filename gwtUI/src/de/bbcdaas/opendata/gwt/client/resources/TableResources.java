package de.bbcdaas.opendata.gwt.client.resources;

import com.google.gwt.user.cellview.client.CellTable;
public interface TableResources extends CellTable.Resources {

    /**
       * The styles applied to the table.
       */
    interface TableStyle extends CellTable.Style {
    }
// CellTable.Style.DEFAULT_CSS,
    @Override
    @Source({"table.css" })
    TableStyle cellTableStyle();

}