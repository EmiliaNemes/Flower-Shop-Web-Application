package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToMany
    @JoinColumn(name = "shopping_cart_id")
    private List<InCartProduct> inCartProducts;

    @OneToOne(cascade=CascadeType.REMOVE)
    private User user;

    public ShoppingCart(List<InCartProduct> inCartProducts, User user) {
        this.inCartProducts = inCartProducts;
        this.user = user;
    }

    public ShoppingCart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<InCartProduct> getProducts() {
        return inCartProducts;
    }

    public void setProducts(List<InCartProduct> inCartProducts) {
        this.inCartProducts = inCartProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
