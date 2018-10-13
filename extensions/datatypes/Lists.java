package extensions.datatypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lists {

    public static String[] getListToStringForOracleInClause(List<String> list) {
        if (null != list) {
            Set<String> set = new HashSet<>();
            set.addAll(list);
            list = new ArrayList<>();
            list.addAll(set);
            int size = list.size();
            if (size > 0) {
                int len = size / 1000;
                String[] listStrings = new String[++len];
                for (int i = 0; i < len; i++) {
                    listStrings[i] = list.subList(0 + i * 1000 > size ? size - 1 : 0 + i * 1000, 1000 + i * 1000 > size ? size : 1000 + i * 1000).toString();
                }
                return listStrings;
            }
        }
        return new String[] { "" };
    }
	
    /**
	 This function returns the sum when a list is passed
     **/
    public static String getSum(List<?> list) {
		if (Commons.isElementNumericalDatatype(list)) {
			BigDecimal sum = BigDecimal.ZERO;
			list.forEach(E -> {
				sum.add(new BigDecimal(((Number)E).doubleValue()));
			});
			return sum.toString();
		} else {
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
			System.err.println(stackTraceElements[1].getClassName() + "."+ stackTraceElements[1].getMethodName()
					+ "() :: Should contain all elements Numerical");
			for (int i = 2; i < stackTraceElements.length; i++ )
				System.err.println(stackTraceElements[i].toString());
		}
    	
    	return null;
    }
	
	/**
	 This function returns the mean when a list is passed
        **/
	public static String getMean(List<?> list) {
		String sum = getSum(list);
		if (!Commons.isEmpty(sum)) {
			return (new BigDecimal(sum)).divide(new BigDecimal(list.size())).toString();
		}
		return null;
    }
}
