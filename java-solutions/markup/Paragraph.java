package markup;
import java.util.List;

public class Paragraph extends ParagraphElement implements MarkupElement {
    public Paragraph(List<MarkupElement> listElements) {
        super(listElements);
    }

    @Override
    public void toMarkdown(StringBuilder result) {
        toMarkdown(result, "");
    }

    @Override
    public void toHtml(StringBuilder result) {
        toHtml(result, "", "");
    }
}
