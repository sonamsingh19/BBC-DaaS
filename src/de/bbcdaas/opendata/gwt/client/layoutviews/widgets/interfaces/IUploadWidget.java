package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import gwtupload.client.Uploader;


import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;

public interface IUploadWidget {
	Uploader getUploader();
    char getDelimeter();
    HasClickHandlers getNextScreenBtn();
    void setNextBtnEnabled(Boolean enabled);
	

}
