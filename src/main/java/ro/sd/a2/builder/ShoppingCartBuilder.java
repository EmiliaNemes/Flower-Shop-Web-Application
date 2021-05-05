package ro.sd.a2.builder;

import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.entity.User;

import java.util.List;

public class ShoppingCartBuilder {

    private ShoppingCart shoppingCart;

    public ShoppingCartBuilder(){
        this.shoppingCart = new ShoppingCart();
    }

    public ShoppingCartBuilder setId(String id) {
        shoppingCart.setId(id);
        return  this;
    }

    public ShoppingCartBuilder setProducts(List<InCartProduct> products) {
        shoppingCart.setProducts(products);
        return this;
    }

    public ShoppingCartBuilder setUser(User user) {
        shoppingCart.setUser(user);
        return this;
    }

    public ShoppingCart build(){
        return shoppingCart;
    }
}
