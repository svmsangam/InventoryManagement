package com.sbmsangam.project1.project1.markdown;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HTMLServiceImpl implements HTMLService {
    @Override
    public String markdownToHtml(String markdown){
        if(markdown.contains("|")){
            return  markdownToHtmlTable(markdown);
        }
            Parser parser = Parser.builder()
                    .build();

            Node document = parser.parse(markdown);
            HtmlRenderer renderer = HtmlRenderer.builder()
                    .build();

            return renderer.render(document);

    }

    @Override
    public String markdownToHtmlTable(String markdown) {
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer htmlRenderer = HtmlRenderer.builder().extensions(extensions).build();
        return htmlRenderer.render(document);
    }
}
