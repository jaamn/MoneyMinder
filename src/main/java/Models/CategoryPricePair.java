package Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CategoryPricePair {

    Category category;
    float price;
    String name;

    public CategoryPricePair(Category category, String name, float price)
    {
        this.category = category;
        this.price = price;
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public StringProperty getCategoryName()
    {
        return new SimpleStringProperty(this.category.getName());
    }

    public StringProperty getPriceProp()
    {
        return new SimpleStringProperty(Float.toString(this.price));
    }

    public StringProperty getItemProp()
    {
        return new SimpleStringProperty(this.name);
    }
}
