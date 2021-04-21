package ie.tudublin;

import processing.core.PApplet;
import processing.data.TableRow;

public class Product
{
    String name;
    float price;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }
           
    // constructor chaining calls the constructor above and passes these values to it
    public Product(TableRow r)
    {
        this(r.getString("Name"), r.getFloat("Price"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [name=" + name + ", price=" + price + "]";
    }

    
}