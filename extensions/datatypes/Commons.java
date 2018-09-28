package extensions.datatypes;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Commons {

    public static boolean isEmpty(Object sSource) {
        boolean returnValue = true;
        // if the object is null, it returns false 
        if (null != sSource) {
            return false;
        }    
        // checks if the object is an instance of String 
       else if (sSource instanceof String) {
            returnValue = Strings.isEmpty((String) sSource);
        //checks if the object is an instance of Collection     
        } else if (sSource instanceof Collection) {
            if (sSource instanceof List<?> && ((List<?>) sSource).size() > 0) {
                returnValue = false;
          //checks if the object is an instance of a Set
            } else if (sSource instanceof Set<?> && ((Set<?>) sSource).size() > 0) {
                returnValue = false;
            }
          // checks if the object is an instance of a map  
        } else if (sSource instanceof Map) {
            if (((Map<?, ?>) sSource).size() > 0) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    
}




