package de.bbcdaas.opendata.gwt.client.layoutviews.interfaces;

import com.google.gwt.user.client.ui.Widget;

public interface IAjaxLoaderView {
 void	stopProcessing();
 void startProcessing(Widget relativeWidget);

}
