package ro.sd.a2.builder;

import ro.sd.a2.entity.Category;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;

import java.util.List;

public class ProductBuilder {
    private Product product;

    public ProductBuilder(){
        this.product = new Product();
    }

    public ProductBuilder setId(String id) {
        product.setId(id);
        return this;
    }

    public ProductBuilder setName(String name) {
        product.setName(name);
        return this;
    }

    public ProductBuilder setColor(String color) {
        product.setColor(color);
        return this;
    }

    public ProductBuilder setCategory(Category category) {
        product.setCategory(category);
        return this;
    }

    public ProductBuilder setPrice(float price) {
        product.setPrice(price);
        return this;
    }

    public ProductBuilder setQuantity(int quantity) {
        product.setQuantity(quantity);
        return this;
    }

    public Product build(){
        return product;
    }
}
