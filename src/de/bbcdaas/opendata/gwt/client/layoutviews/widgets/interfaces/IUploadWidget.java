package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces;

import gwtupload.client.Uploader;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface IUploadWidget {
	Uploader getUploader();
    char getDelimeter();
    HasClickHandlers getNextScreenBtn();
	

}
