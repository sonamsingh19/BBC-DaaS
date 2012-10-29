package de.bbcdaas.opendata.gwt.client.layoutviews.widgets.TagCloud;


@SuppressWarnings("serial")
public class WordTag extends Tag{
    private String word;
    private String color;
    
    public WordTag() {
        super();
    }

    public WordTag(String word) {
        this.word = word;
        numberOfOccurences = 1;
        color=null;
    }

    public WordTag(String word, String link) {
        this(word);
        this.link = link;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
    
    public String getColor() {
                return color;
        }

        public void setColor(String color) {
                this.color = color;
        }

        @Override
    public int hashCode() {
        return word.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!( obj instanceof Tag)){
            return false;
        }
        return word.equals(((WordTag)obj).getWord());
    }


}
