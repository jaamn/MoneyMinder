package Utils.SQL.QueryFactory;

import Models.Tables;
import Utils.SQL.QueryStatements.SelectQueries.SelectFromUsers;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;

public class SelectQueryFactory {
    public static SelectQuery getQuery(Tables name)
    {
        /*
        if (name.equals(Tables.Categories))
        {
            return new SelectFromCategories();
        }
        if (name.equals(Tables.Items))
        {
            return new SelectFromItems();
        }
        if (name.equals(Tables.Receipts))
        {
            return new SelectFromReceipts();
        }
        if (name.equals(Tables.Stores))
        {
            return new SelectFromStores();
        }
        */
        if (name.equals(Tables.Users))
        {
            return new SelectFromUsers();
        }

        return null;
    }
}
