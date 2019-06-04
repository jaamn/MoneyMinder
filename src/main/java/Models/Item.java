package Models;

import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static ObservableList<Item> getItemsForUser(User user)
    {
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Items);
        ObservableList<Item> items = FXCollections.observableArrayList();
        try (ResultSet rs = query.execute(user))
        {
            while (rs.next())
            {

                String name = rs.getString("name");
                int cid = rs.getInt("cid");
                int rid = rs.getInt("rid");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");

                Item item = new Item(rid, cid, name, price, quantity);
                items.add(item);
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
        prop.setValue(Float.toString(this.rid));
        return prop;
    }

    public StringProperty getMinProperty(User user, Category c)
    {
        StringProperty minProp = new SimpleStringProperty();
        minProp.setValue(Float.toString(getMinPriceForCategory(user, c)));
        return minProp;
    }

    public StringProperty getMaxProperty(User user, Category c)
    {
        StringProperty maxProp = new SimpleStringProperty();
        maxProp.setValue(Float.toString(getMaxPriceForCategory(user, c)));
        return maxProp;
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

    public static float getMinPriceForCategory(User user, Category c)
    {
        SelectQuery query = SelectQueryFactory.getAggregateQuery(Tables.Items, "MIN");
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

    public static float getMaxPriceForCategory(User user, Category c)
    {
        SelectQuery query = SelectQueryFactory.getAggregateQuery(Tables.Items, "MAX");
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
}
