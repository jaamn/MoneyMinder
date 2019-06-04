package Utils.SQL.QueryFactory;

import Models.Tables;
import Utils.SQL.QueryStatements.SelectQueries.*;

public class SelectQueryFactory {
    public static SelectQuery getQuery(Tables name)
    {
        if (name.equals(Tables.Categories))
        {
            return new SelectFromCategories();
        }
        if (name.equals(Tables.Items))
        {
            return new SelectFromItems();
        }
        if (name.equals(Tables.Stores))
        {
            return new SelectFromStores();
        }
        if (name.equals(Tables.Users))
        {
            return new SelectFromUsers();
        }
        if (name.equals(Tables.Receipts))
        {
            return new SelectFromReceipts();
        }

        return null;
    }

    public static SelectQuery getAggregateQuery(Tables name, String agg)
    {
        if (name.equals(Tables.Items))
        {
            return new SelectAggregateFromItems(agg);
        }
        return null;
    }
}
