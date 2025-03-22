package org.notetaking.markdownnotetakingapi.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.springframework.stereotype.Component;


@Component
public class Converter {
    private final Parser parser;
    private final HtmlRenderer renderer;

    public Converter() {
        this.parser = Parser.builder().build();
        this.renderer = HtmlRenderer.builder().build();
    }

    public String toHtml(String markdownContent) {
        Node document = parser.parse(markdownContent);
        return renderer.render(document);
    }
}
