package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "in_cart_product")
public class InCartProduct {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="color")
    private String color;

    @Column (name = "category")
    private Category category;

    @Column (name = "price")
    private float price;

    @Column (name = "quantity")
    private int quantity;

    @Column
    private boolean ordered;

    @ManyToOne
    private ShoppingCart shoppingCart;

    @ManyToOne
    private Order order;

    public InCartProduct(String name, String color, Category category, float price, int quantity) {
        this.name = name;
        this.color = color;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public InCartProduct() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
