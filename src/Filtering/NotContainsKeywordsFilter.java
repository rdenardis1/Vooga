package filtering;
import model.Event;

public class NotContainsKeywordsFilter extends AbstractKeywordsFilter {
	
	public boolean checkFilterCondition(Event event, Object ... args) {
		return !containsKeywords(event, (String) args[0]);
	}
}
