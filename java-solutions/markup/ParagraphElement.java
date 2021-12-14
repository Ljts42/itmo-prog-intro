package markup;

import java.util.List;

public abstract class ParagraphElement implements MarkupElement {
    protected final List<MarkupElement> listElements;

    protected ParagraphElement(List<MarkupElement> listElements) {
        this.listElements = listElements;
    }

    protected void toMarkdown(StringBuilder result, String tag) {
        result.append(tag);
        for (MarkupElement element : listElements) {
            element.toMarkdown(result);
        }
        result.append(tag);
    }

    protected void toHtml(StringBuilder result, String tag) {
        result.append('<').append(tag).append('>');
        for (MarkupElement element : listElements) {
            element.toHtml(result);
        }
        result.append("</").append(tag).append('>');
    }
}
