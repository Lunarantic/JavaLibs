package extensions.databases;

import extensions.datatypes.Commons;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OracleDB {

    public static void close(Connection connection, Statement ps, ResultSet rs) throws SQLException {
        if (null != connection)
            connection.close();

        if (null != ps)
            ps.close();

        if (null != rs)
            rs.close();
    }

    public static Map<String, Map<String, Object>> rsToMap(final ResultSet rs, String rowKey) throws SQLException {

        int iFetchSize = 5000;
        if (rs.getFetchSize() < iFetchSize) {
            rs.setFetchSize(iFetchSize);
        }

        Map<String, Map<String, Object>> rMap = new LinkedHashMap<>(iFetchSize + iFetchSize / 10);

        int rowKeyTemp = 0;
        boolean generateKey = false;
        String rowKeyStr;

        if (Commons.isEmpty(rowKey)) {
            generateKey = true;
        } else {
            rowKey = rowKey.toLowerCase();
        }

        ResultSetMetaData rsMetaData = rs.getMetaData();

        while (rs.next()) {
            int columnCount = rsMetaData.getColumnCount();
            Map<String, Object> innerMap = new HashMap<>(columnCount + 10, 1);
            for(int i = 1; i <= columnCount; i++) {
                String colName = rsMetaData.getColumnName(i);
                int columnType = rsMetaData.getColumnType(i);
                Object colValue = null;

                if (Types.TIMESTAMP == columnType) {
                    Timestamp timestamp = rs.getTimestamp(colName);
                    if (null != timestamp) {
                        colValue = timestamp.getTime();
                    }
                } else if (Types.DATE == columnType) {
                    Timestamp date = rs.getTimestamp(colName);
                    if (null != date) {
                        colValue = date.getTime();
                    }
                } else if (Types.NUMERIC == columnType || Types.INTEGER == columnType) {
                    colValue = rs.getDouble(colName);
                } else {
                    colValue = rs.getString(colName);
                }

                if (!Commons.isEmpty(colValue)) {
                    innerMap.put(colName.toLowerCase(), colValue);
                }
            }
            if (generateKey) {
                rowKeyStr = ++rowKeyTemp + "";
            } else {
                rowKeyStr = innerMap.get(rowKey) + "";
            }
            rMap.put(rowKeyStr, innerMap);
        }

        return rMap;
    }

}