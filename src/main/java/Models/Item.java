package Models;

import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.util.StringJoiner;

public class Item {

    private int iid;
    private int rid;
    private int cid;
    private int quantity;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\tiid INTEGER PRIMARY KEY");
        sj.add("rid INTEGER");
        sj.add("cid INTEGER");
        sj.add("quantity INTEGER");
        sj.add("FOREIGN KEY(rid) REFERENCES Receipts(rid)");
        sj.add("FOREIGN KEY(cid) REFERENCES Categories(cid)");

        return sj.toString();
    }

    public int getRid() {
        return rid;
    }

    public int getCid() {
        return cid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public StringProperty getCategoryName()
    {
        StringProperty prop = new SimpleStringProperty();
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Categories);
        try (ResultSet rs = query.execute(this.cid))
        {
            if (rs.next())
            {
                String name = rs.getString("name");
                prop.setValue(name);
                return prop;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public StringProperty quantityProperty()
    {
        StringProperty prop = new SimpleStringProperty();
        prop.setValue(Integer.toString(this.quantity));
        return prop;
    }
}
