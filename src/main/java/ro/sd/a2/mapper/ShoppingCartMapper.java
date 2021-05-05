package ro.sd.a2.mapper;

import ro.sd.a2.dto.ShoppingCartDto;
import ro.sd.a2.entity.ShoppingCart;

public class ShoppingCartMapper {

    public static ShoppingCartDto entityToDto(ShoppingCart shoppingCart){
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUser(shoppingCart.getUser());
        shoppingCartDto.setProducts(shoppingCart.getProducts());

        return shoppingCartDto;
    }

    public static ShoppingCart dtoToEntity(ShoppingCartDto shoppingCartDto){
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDto.getId());
        shoppingCart.setUser(shoppingCartDto.getUser());
        shoppingCart.setProducts(shoppingCartDto.getProducts());

        return shoppingCart;
    }
}
