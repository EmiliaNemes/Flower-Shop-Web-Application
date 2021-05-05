package ro.sd.a2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "fk_client")
    private User user;

    @Column (name = "total_price")
    private float totalPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column (name = "date")
    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<InCartProduct> inCartProducts;

    public Order(User user, float totalPrice) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.totalPrice = totalPrice;
        this.date = LocalDate.now();
    }

    public Order() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<InCartProduct> getProducts() {
        return inCartProducts;
    }

    public void setProducts(List<InCartProduct> products) {
        this.inCartProducts = products;
    }
}
