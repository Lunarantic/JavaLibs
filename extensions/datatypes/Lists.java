package extensions.datatypes;

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
}
