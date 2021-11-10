package markup;

import java.util.List;

public class Strikeout extends ParagraphElement implements MarkupElement {
    public Strikeout(List<MarkupElement> listElements) {
        super(listElements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "~");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "<s>", "</s>");
    }
}
