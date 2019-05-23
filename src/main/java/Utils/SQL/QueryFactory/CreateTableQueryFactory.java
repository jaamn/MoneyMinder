package Utils.SQL.QueryFactory;

import Utils.SQL.QueryStatements.CreateTableQueries.*;
import Models.Tables;

public class CreateTableQueryFactory {
    public static CreateTableQuery getQuery(Tables name)
    {
        if (name.equals(Tables.Categories))
        {
            return new CreateCategoriesTable();
        }
        /*
        if (name.equals(Tables.Items))
        {
            return new CreateItemsTable();
        }
        if (name.equals(Tables.Receipts))
        {
            return new CreateReceiptsTable();
        }
        if (name.equals(Tables.Stores))
        {
            return new CreateStoresTable();
        }
        */
        if (name.equals(Tables.Users))
        {
            return new CreateUsersTable();
        }

        return null;
    }
}
