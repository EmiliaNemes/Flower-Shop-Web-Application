package ro.sd.a2.builder;

import ro.sd.a2.entity.*;

public class InCartProductBuilder {

    private InCartProduct inCartProduct;

    public InCartProductBuilder(){
        this.inCartProduct = new InCartProduct();
    }

    public InCartProductBuilder setId(String id) {
        inCartProduct.setId(id);
        return this;
    }

    public InCartProductBuilder setName(String name) {
        inCartProduct.setName(name);
        return this;
    }

    public InCartProductBuilder setColor(String color) {
        inCartProduct.setColor(color);
        return this;
    }

    public InCartProductBuilder setCategory(Category category) {
        inCartProduct.setCategory(category);
        return this;
    }

    public InCartProductBuilder setPrice(float price) {
        inCartProduct.setPrice(price);
        return this;
    }

    public InCartProductBuilder setQuantity(int quantity) {
        inCartProduct.setQuantity(quantity);
        return this;
    }

    public InCartProductBuilder setOrdered(boolean ordered){
        inCartProduct.setOrdered(ordered);
        return this;
    }

    public InCartProductBuilder setOrder(Order order){
        inCartProduct.setOrder(order);
        return this;
    }

    public InCartProductBuilder setShoppingCart(ShoppingCart shoppingCart){
        inCartProduct.setShoppingCart(shoppingCart);
        return this;
    }

    public InCartProduct build(){
        return inCartProduct;
    }
}
