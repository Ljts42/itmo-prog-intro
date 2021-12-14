package markup;

import java.util.List;

public class Emphasis extends ParagraphElement {
    public Emphasis(List<MarkupElement> listElements) {
        super(listElements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "*");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "em");
    }
}
