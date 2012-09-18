package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import com.google.gwt.event.dom.client.HasClickHandlers;

import gwtupload.client.Uploader;

public interface IUploadWidget {
	Uploader getUploader();
    char getDelimeter();
    HasClickHandlers getNextScreenBtn();
	

}
