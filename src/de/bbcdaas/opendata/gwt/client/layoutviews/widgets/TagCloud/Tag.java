package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.TagCloud;

import java.io.Serializable;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;

public abstract class Tag extends Composite  implements Serializable{

    private static final long serialVersionUID = 2153391657389702774L;
    private String id;
    private int orientation;
    
    private ClickHandler clickhandler;
    
    protected String link;
    protected int numberOfOccurences;
    
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL_LEFT = 1;
    public static final int VERTICAL_RIGHT = 2;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumberOfOccurences() {
        return numberOfOccurences;
    }
    
    public void setNumberOfOccurences(int occurency){
        numberOfOccurences = occurency;
    }

    /**
     * Increase the <code>numberOfOccurences</code> variable
     */
    public void increaseNumberOfOccurences() {
        numberOfOccurences++;
    }
    
    @Override
    public abstract int hashCode();
    
    @Override
    public abstract boolean equals(Object obj);

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getOrientation() {
        return orientation;
    }
    
    public void setClickHandler(ClickHandler handler){
    	this.clickhandler= handler;
    }

	public ClickHandler getClickhandler() {
		return clickhandler;
	}

	

}