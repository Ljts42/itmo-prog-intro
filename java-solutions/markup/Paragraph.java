package markup;
import java.util.List;

public class Paragraph extends ParagraphElement {
    public Paragraph(List<MarkupElement> listElements) {
        super(listElements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toHtml(StringBuilder result) {
        for (MarkupElement element : listElements) {
            element.toHtml(result);
        }
    }
}
