package extensions.datatypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Commons {

    public static boolean isEmpty(Object input) {
        if (null == input) {
            return true;
        } else if (input instanceof String) {
            return Strings.isEmpty((String) input);
        } else if (input instanceof Collection) {
            if (input instanceof List<?> && ((List<?>) input).size() == 0) {
                return true;
            } else if (input instanceof Set<?> && ((Set<?>) input).size() == 0) {
                return true;
            }
        } else if (input instanceof Map<> && ((Map<?, ?>) input).size() == 0) {
            return true;
        } 
        return false;
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
