package markup;

public interface MarkupElement {
    void toMarkdown(StringBuilder result);
    void toHtml(StringBuilder result);
}
