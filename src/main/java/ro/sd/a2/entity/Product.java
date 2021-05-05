package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(min=1, message = "Name must not be empty!")
    @Pattern(regexp = "([A-Z][a-z]*)", message = "Name must start with uppercase letter and must contain only letters!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min=1, message = "Color must not be empty!")
    @Pattern(regexp = "([A-Z][a-z]*)", message="Color must start with uppercase letter and must contain only letters!")
    @Column (name = "color")
    private String color;

    @NotNull
    @Column (name = "category")
    private Category category;

    @NotNull
    @Min(value = 0, message = "Price must be a positive number!")
    @Column (name = "price")
    private float price;

    @NotNull
    @Min(value = 0, message = "Quantity must be a positive number!")
    @Column (name = "quantity")
    private int quantity;

    public Product(String name, String color, Category category, float price, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.color = color;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() { }

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

}
