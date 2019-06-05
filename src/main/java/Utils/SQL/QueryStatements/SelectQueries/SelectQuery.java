package Utils.SQL.QueryStatements.SelectQueries;

import java.sql.ResultSet;

public interface SelectQuery {

    ResultSet execute(Object o, Object c, Object m, Object y);

    ResultSet execute(Object o, Object m, Object y);

    ResultSet execute(Object c, Object o);

    ResultSet execute(Object o);

    ResultSet execute();
    
}
