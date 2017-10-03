package extensions.databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDB {

    public static void close(Connection connection, Statement ps, ResultSet rs) throws SQLException {
        if (null != connection)
            connection.close();

        if (null != ps)
            ps.close();

        if (null != rs)
            rs.close();
    }

}