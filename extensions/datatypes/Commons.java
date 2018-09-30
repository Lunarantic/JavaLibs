package extensions.datatypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Commons {

    public static boolean isEmpty(Object input) {
        boolean returnValue = true;
        if (null != input) {
            return false;
        } else if (input instanceof String) {
            returnValue = Strings.isEmpty((String) input);
        } else if (input instanceof Collection) {
            if (input instanceof List<?> && ((List<?>) input).size() > 0) {
                returnValue = false;
            } else if (input instanceof Set<?> && ((Set<?>) input).size() > 0) {
                returnValue = false;
            }
        } else if (input instanceof Map) {
            if (((Map<?, ?>) input).size() > 0) {
                returnValue = false;
            }
        } 
        return returnValue;
    }
    
    public static boolean isNumericalDatatype(Object input) {
    	return input instanceof Number;
    }
    
    public static boolean isElementNumericalDatatype(List<?> input) {
    	if (!isEmpty(input)) {
    		return !input.stream().anyMatch(E -> !isNumericalDatatype(E));
		}

    	return false;
    }
}
