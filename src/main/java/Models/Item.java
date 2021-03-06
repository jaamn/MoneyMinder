package Models;

import Utils.SQL.QueryFactory.InsertQueryFactory;
import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.InsertQueries.InsertQuery;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.StringJoiner;

public class Item {

    private int iid;
    private int rid;
    private int cid;
    private String name;
    private float price;
    private int quantity;

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n\t");
        sj.add("\tiid INTEGER PRIMARY KEY");
        sj.add("rid INTEGER");
        sj.add("cid INTEGER");
        sj.add("name TEXT");
        sj.add("price FLOAT");
        sj.add("quantity INTEGER");
        sj.add("FOREIGN KEY(rid) REFERENCES Receipts(rid)");
        sj.add("FOREIGN KEY(cid) REFERENCES Categories(cid)");

        return sj.toString();
    }

    public Item()
    {
    }

    public Item(int rid, int cid, String name, float price, int quantity) {
        this.rid = rid;
        this.cid = cid;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void insertIntoDB()
    {
        InsertQuery insertItem = InsertQueryFactory.getQuery(Tables.Items);
        insertItem.execute(this);
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

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static ObservableList<ItemReceiptPair> getItemsForUser(User user)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Items);
        ObservableList<ItemReceiptPair> items = FXCollections.observableArrayList();
        try (ResultSet rs = query.execute(user))
        {
            while (rs.next())
            {
                String name = rs.getString("name");
                int cid = rs.getInt("cid");
                int rid = rs.getInt("rid");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                int sid = rs.getInt("sid");
                String date = rs.getString("date");

                Item item = new Item(rid, cid, name, price, quantity);
                Receipt receipt = new Receipt(rid, sid, user.getUsername(), Date.valueOf(date));
                ItemReceiptPair irPair = new ItemReceiptPair(item, receipt);

                items.add(irPair);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return items;
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

    public StringProperty nameProperty()
    {
        StringProperty prop = new SimpleStringProperty(this.name);
        return prop;
    }

    public StringProperty priceProperty()
    {
        StringProperty prop = new SimpleStringProperty();
        prop.setValue(Float.toString(this.price));
        return prop;
    }

    public StringProperty ridProperty()
    {
        StringProperty prop = new SimpleStringProperty();
        prop.setValue(Integer.toString(this.rid));
        return prop;
    }

    public static float getSumPriceForCategory(User user, Category c)
    {
        SelectQuery query = SelectQueryFactory.getAggregateQuery(Tables.Items, "SUM");
        try (ResultSet rs = query.execute(c, user))
        {
            if (rs.next())
            {
                float sum = rs.getFloat("aggPrice");
                return sum;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static CategoryPricePair getMinPriceForCategoryForMonth(User user, Category c, String month, String year)
    {
        SelectQuery query = SelectQueryFactory.getAggregateQuery(Tables.Items, "MIN");
        try (ResultSet rs = query.execute(c, user, month, year))
        {
            if (rs.next())
            {
                float min = rs.getFloat("aggPrice");
                String name = rs.getString("name");
                return new CategoryPricePair(c, name, min);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static CategoryPricePair getMaxPriceForCategoryForMonth(User user, Category c, String month, String year)
    {
        SelectQuery query = SelectQueryFactory.getAggregateQuery(Tables.Items, "MAX");
        try (ResultSet rs = query.execute(c, user, month, year))
        {
            if (rs.next())
            {
                float max = rs.getFloat("aggPrice");
                String name = rs.getString("name");
                return new CategoryPricePair(c, name, max);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }
}
