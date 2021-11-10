package markup;

import java.util.List;

public abstract class ParagraphElement {
    private List<MarkupElement> listElements;

    public ParagraphElement(List<MarkupElement> listElements) {
        this.listElements = listElements;
    }

    protected void toMarkdown(StringBuilder result, String border) {
        result.append(border);
        for (MarkupElement element : listElements) {
            element.toMarkdown(result);
        }
        result.append(border);
    }

    protected void toHtml(StringBuilder result, String left, String right) {
        result.append(left);
        for (MarkupElement element : listElements) {
            element.toHtml(result);
        }
        result.append(right);
    }
}
