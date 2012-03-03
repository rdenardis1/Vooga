package parsing;

import org.jdom.Element;
import org.joda.time.DateTime;

public class AbstractSimpleTimeWithSeparateDateAndTimeXMLParser extends
        AbstractSimpleTimeXMLParser {

	private String myStartDate;
	private String myStartTime;
	private String myEndDate;
	private String myEndTime;

	protected AbstractSimpleTimeWithSeparateDateAndTimeXMLParser(
	        String rootNode, String eventNode, String title,
	        String description, String location, String dateTimePattern,
	        String startDate, String startTime, String endDate, String endTime) {
		super(rootNode, eventNode, title, description, location, dateTimePattern, "start", "end");
		myStartDate = startDate;
		myStartTime = startTime;
		myEndDate = endDate;
		myEndTime = endTime;
	}

	/**
	 * Merges the date and time elements of an event,
	 * 
	 * @return a String of the combined information
	 */
	private String mergeDateAndTime(Element event, String dateTag,
	        String timeTag) {
		String date = event.getChildText(dateTag);
		String time = event.getChildText(timeTag);
		return date + time.split(" ")[0];
	}
	
	@Override
	protected String getTimestamp(Element time, String attrib) {
		if(attrib.equals("start"))
			return mergeDateAndTime(time, myStartDate, myStartTime);
		return mergeDateAndTime(time, myEndDate, myEndTime);
	}

	@Override
	protected DateTime parseStartTime(Element event) {
		return super.parseTime(event, "start");
	}

	@Override
	protected DateTime parseEndTime(Element event) {
		return super.parseTime(event, "end");

	}
}
