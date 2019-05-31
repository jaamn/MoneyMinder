package Utils.SQL.QueryFactory;

import Models.Tables;
import Utils.SQL.QueryStatements.InsertQueries.InsertIntoUsers;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;

public class InsertQueryFactory {
    public static InsertQuery getQuery(Tables name)
    {
        /*
        if (name.equals(Tables.Categories))
        {
            return new InsertIntoCategories();
        }
        if (name.equals(Tables.Items))
        {
            return new InsertIntoItems();
        }
        if (name.equals(Tables.Receipts))
        {
            return new InsertIntoReceipts();
        }
        if (name.equals(Tables.Stores))
        {
            return new InsertIntoStores();
        }
        */
        if (name.equals(Tables.Users))
        {
            return new InsertIntoUsers();
        }

        return null;
    }
}
