package ro.sd.a2.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name = "fk_client")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;

    @Column (name = "value")
    private float value;

    @Column (name = "category")
    private String category;

    @Column (name = "expiration_date")
    private LocalDate expirationDate;

    public Voucher(User user, Order order, float value, String category, LocalDate expirationDate) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.order = order;
        this.value = value;
        this.category = category;
        this.expirationDate = expirationDate;
    }

    public Voucher() { }

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
