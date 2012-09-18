package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.thirdparty;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

/**
 * This widget is to create <p> elements in a page.
 */
public class Paragraph extends Widget implements HasText {

    public Paragraph() {
        setElement(DOM.createElement("p"));
    }

    public Paragraph(String text) {
        this();
        setText(text);
    }

    @Override
	public String getText() {
        return getElement().getInnerText();
    }

    @Override
	public void setText(String text) {
        getElement().setInnerText(text);
    }
}
