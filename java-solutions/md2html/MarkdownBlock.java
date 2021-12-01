package md2html;

import java.util.Map;
import java.util.HashMap;

public class MarkdownBlock {
    private StringBuilder markdown;
    private String cur = "";
    private int position = 0;

    private final Map<String, String> tags = Map.of(
        "*", "em",
        "_", "em",
        "**", "strong",
        "__", "strong",
        "--", "s",
        "`", "code",
        "''", "q"
    );

    private Map<String, PairMd2Html> parts = new HashMap<>(Map.of(
        "", new PairMd2Html(),
        "*", new PairMd2Html(),
        "_", new PairMd2Html(),
        "**", new PairMd2Html(),
        "__", new PairMd2Html(),
        "--", new PairMd2Html(),
        "`", new PairMd2Html(),
        "''", new PairMd2Html()
    ));

    public MarkdownBlock(StringBuilder markdown) {
        this.markdown = markdown;
    }

    private boolean checkTag(int n, StringBuilder result) {
        if (markdown.length() < position + n) {
            return false;
        }
        String tag = markdown.substring(position, position + n);
        if (tags.containsKey(tag)) {
            if (cur.equals(tag)) {
                StringBuilder text = new StringBuilder();
                text.append('<').append(tags.get(cur)).append('>');
                text.append(parts.get(cur).text);
                text.append("</").append(tags.get(cur)).append('>');
                cur = parts.get(cur).prev;
                parts.get(cur).text.append(text);
            } else {
                PairMd2Html p = new PairMd2Html(new StringBuilder(), cur);
                cur = tag;
                parts.put(cur, p);
            }
            return true;
        }
        return false;
    }

    private void parseText(StringBuilder result) {
        for (;position < markdown.length(); position++) {
            switch (markdown.charAt(position)) {
                case '<':
                    parts.get(cur).text.append("&lt;");
                    break;
                case '>':
                    parts.get(cur).text.append("&gt;");
                    break;
                case '&':
                    parts.get(cur).text.append("&amp;");
                    break;
                case '\\':
                    if (position + 1 < markdown.length()) {
                        parts.get(cur).text.append(markdown.charAt(++position));
                    }
                    break;
                default:
                    if (checkTag(2, result)) {
                        position++;
                    } else if (!checkTag(1, result)) {
                        parts.get(cur).text.append(markdown.charAt(position));
                    }
                    break;
            }
        }

        while (!cur.equals("")) {
            StringBuilder text = new StringBuilder();
            text.append(cur).append(parts.get(cur).text);
            cur = parts.get(cur).prev;
            parts.get(cur).text.append(text);
        }
        result.append(parts.get(cur).text);
    }

    private void parseHeader() {
        while (position < markdown.length() &&
               markdown.charAt(position) == '#') {
            position++;
        }
        if (position == markdown.length() || markdown.charAt(position) != ' ') {
            position = 0;
        }
    }

    public void toHtml(StringBuilder result) {
        parseHeader();
        int headerLevel = position;
        if (headerLevel > 0) {
            result.append("<h").append(position++).append(">");
            parseText(result);
            result.append("</h").append(headerLevel).append(">");
        } else {
            result.append("<p>");
            parseText(result);
            result.append("</p>");
        }
    }
}