package de.bbcdaas.opendata.gwt.client.layoutviews.widgets;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.bbcdaas.opendata.gwt.client.layoutviews.widgets.interfaces.IDataSetDescriptionWidget;

public class DataDescriptionWidget extends Composite implements
		IDataSetDescriptionWidget {

	private static DataDescriptionWidgetUiBinder uiBinder = GWT
			.create(DataDescriptionWidgetUiBinder.class);
	@UiField
	TextBox name;
	@UiField
	TextArea description;
	@UiField
	PushButton publishBtn;
	@UiField(provided=true)
	final AutoSuggestForm autoSuggestForm;

	interface DataDescriptionWidgetUiBinder extends
			UiBinder<Widget, DataDescriptionWidget> {
	}

	
	public DataDescriptionWidget(AutoSuggestForm autoSuggestForm) {

		this.autoSuggestForm=autoSuggestForm;
		initWidget(uiBinder.createAndBindUi(this));

		description.getElement().getStyle().setProperty("maxWidth", "500px");
		description.getElement().getStyle().setProperty("maxHeight", "150px");
 
	}

	@Override
	public String getName() {

		return name.getValue();
	}

	@Override
	public String getDescription() {
	
		return description.getValue();
				
	}

	@Override
	public ArrayList<String> getTags() {
		ArrayList<String> tags = new ArrayList<String>();
		String tagString = autoSuggestForm.getText();
		String[] tagsAraay = tagString.split(",");
		for (int i= 0; i < tagsAraay.length; i++) {
			if(tagsAraay[i].length()>1 )
				tags.add(tagsAraay[i]);
		}

		return tags;
	}

	@Override
	public HasClickHandlers getPublishClickHandlers() {
		return publishBtn;
	}

	@Override
	public void setTags(ArrayList<String> tags) {

		
	}

}
