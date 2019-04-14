package common.solution.utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class XmlDomUtils {
    private XmlDomUtils() {
    }

    public static String getOnlyElementTextContent(Element elementSource, String tagName) {
        NodeList elementsByTagName = elementSource.getElementsByTagName(tagName);
        return elementsByTagName.item(0).getTextContent();
    }
}
