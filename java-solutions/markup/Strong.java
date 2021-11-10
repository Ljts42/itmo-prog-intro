package markup;

import java.util.List;

public class Strong extends ParagraphElement implements MarkupElement {
    public Strong(List<MarkupElement> listElements) {
        super(listElements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "__");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<strong>", "</strong>");
    }
}
