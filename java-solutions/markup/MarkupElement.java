package markup;

public interface MarkupElement {
    public void toMarkdown(StringBuilder result);
    public void toHtml(StringBuilder result);
}
