package extensions.datatypes;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Commons {

    public static boolean isEmpty(Object sSource) {
        boolean returnValue = true;
        if (sSource instanceof String) {
            returnValue = isEmpty((String) sSource);
        } else if (sSource instanceof Collection) {
            if (sSource instanceof List<?> && ((List<?>) sSource).size() > 0) {
                returnValue = false;
            } else if (sSource instanceof Set<?> && ((Set<?>) sSource).size() > 0) {
                returnValue = false;
            }
        } else if (sSource instanceof Map) {
            if (((Map<?, ?>) sSource).size() > 0) {
                returnValue = false;
            }
        } else if (null != sSource) {
            return false;
        }
        return returnValue;
    }

    public static boolean isEmpty(String sSource) {
        if (null == sSource || "".equals(sSource.trim()) || "null".equalsIgnoreCase(sSource.trim())) {
            return true;
        }
        return false;
    }
}
