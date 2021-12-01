package md2html;

public class PairMd2Html {
    public StringBuilder text;
    public String prev;

    PairMd2Html() {
        this.text = new StringBuilder();
        this.prev = "";
    }

    PairMd2Html(StringBuilder text, String prev) {
        this.text = text;
        this.prev = prev;
    }
}
