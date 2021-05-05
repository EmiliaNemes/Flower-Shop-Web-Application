package ro.sd.a2.builder;

import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.User;

import java.time.LocalDate;
import java.util.List;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(){
        this.order = new Order();
    }

    public OrderBuilder setId(String id) {
        order.setId(id);
        return this;
    }

    public OrderBuilder setUser(User user) {
        order.setUser(user);
        return this;
    }

    public OrderBuilder setTotalPrice(float totalPrice) {
        order.setTotalPrice(totalPrice);
        return this;
    }

    public OrderBuilder setDate(LocalDate date) {
        order.setDate(date);
        return this;
    }

    public OrderBuilder setInCartProducts(List<InCartProduct> products) {
        order.setProducts(products);
        return this;
    }

    public Order build(){
        return order;
    }
}
