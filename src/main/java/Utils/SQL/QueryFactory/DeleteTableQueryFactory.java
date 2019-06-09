package Utils.SQL.QueryFactory;

import Utils.SQL.QueryStatements.DeleteTableQueries.*;
import Models.Tables;


public class DeleteTableQueryFactory {
    public static DeleteTableQuery getQuery(Tables name)
    {
        if (name.equals(Tables.Categories))
        {
            return new DeleteCategoriesTable();
        }
        if (name.equals(Tables.Items))
        {
            return new DeleteItemsTable();
        }
        if (name.equals(Tables.Receipts))
        {
            return new DeleteReceiptsTable();
        }
        if (name.equals(Tables.Stores))
        {
            return new DeleteStoresTable();
        }
        if (name.equals(Tables.Users))
        {
            return new DeleteUsersTable();
        }

        return null;
    }
}
