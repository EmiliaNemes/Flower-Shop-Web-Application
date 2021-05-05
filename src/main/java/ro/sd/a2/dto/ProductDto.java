package ro.sd.a2.dto;

import ro.sd.a2.entity.Category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProductDto {

    private String id;

    @NotNull
    @Size(min=1, message = "Name must not be empty!")
    @Pattern(regexp = "([A-Z][a-z]*)", message = "Name must start with uppercase letter and must contain only letters!")
    private String name;

    @NotNull
    @Size(min=1, message = "Color must not be empty!")
    @Pattern(regexp = "([A-Z][a-z]*)", message="Color must start with uppercase letter and must contain only letters!")
    private String color;

    @NotNull(message = "Category must not be empty!")
    private Category category;

    @NotNull
    @Min(value = 0, message = "Price must be a positive number!")
    private float price;

    @NotNull
    @Min(value = 0, message = "Quantity must be a positive number!")
    private int quantity;

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
