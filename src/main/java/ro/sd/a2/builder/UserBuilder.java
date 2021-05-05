package ro.sd.a2.builder;

import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Role;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.entity.User;

import java.util.List;

public class UserBuilder {

    private User user;

    public UserBuilder(){
        this.user = new User();
    }

    public UserBuilder setId(String id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder setEmailAddress(String emailAddress) {
        user.setEmailAddress(emailAddress);
        return this;
    }

    public UserBuilder setPhoneNumber(String phoneNumber) {
        user.setPhoneNumber(phoneNumber);
        return this;
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setRole(Role role) {
        user.setRole(role);
        return this;
    }

    public UserBuilder setOrders(List<Order> orders){
        user.setOrders(orders);
        return this;
    }

    public UserBuilder setShoppingCart(ShoppingCart shoppingCart){
        user.setShoppingCart(shoppingCart);
        return this;
    }

    public User getUser(){
        return user;
    }

    public User build(){
        return user;
    }
}
