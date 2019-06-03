package Models;

import Utils.SQL.QueryFactory.SelectQueryFactory;
import Utils.SQL.QueryStatements.SelectQueries.SelectQuery;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Category {

    private int cid;
    private String name;

    public Category(int cid, String name) {
        this.cid = cid;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static String fieldsForTableCreation()
    {
        StringJoiner sj = new StringJoiner(",\n");
        sj.add("\tcid INTEGER PRIMARY KEY");
        sj.add("\tname TEXT");

        return sj.toString();
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public static ObservableList<Category> getCategories()
    {
        StringProperty prop = new SimpleStringProperty();
        SelectQuery query = SelectQueryFactory.getQuery(Tables.Categories);
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try (ResultSet rs = query.execute())
        {
            while (rs.next())
            {
                String name = rs.getString("name");
                String cid = rs.getString("cid");
                System.out.println("cid: " + cid + " name: " + name);
                categories.add(new Category(Integer.parseInt(cid), name));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return categories;
    }

    static public List<Category> getPresetCategories()
    {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Groceries"));
        categories.add(new Category(2, "Personal"));
        categories.add(new Category(3, "Entertainment"));
        return categories;
    }
}
