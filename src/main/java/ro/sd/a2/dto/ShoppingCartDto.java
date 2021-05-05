package ro.sd.a2.dto;

import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.User;

import java.util.List;

public class ShoppingCartDto {

    private String id;
    private List<InCartProduct> products;
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InCartProduct> getProducts() {
        return products;
    }

    public void setProducts(List<InCartProduct> products) {
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
