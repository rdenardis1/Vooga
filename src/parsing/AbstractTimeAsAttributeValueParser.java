package parsing;

import org.jdom.Element;

public class AbstractTimeAsAttributeValueParser extends AbstractSimpleTimeXMLParser{

	protected AbstractTimeAsAttributeValueParser(String rootNode,
            String eventNode, String title, String description,
            String location, String dateTimePattern, String start, String end) {
	    super(rootNode, eventNode, title, description, location, dateTimePattern,
	            start, end);
    }

	@Override
	protected String getTimestamp(Element time, String attrib) {
		return time.getAttributeValue(attrib);
	}
	
}
