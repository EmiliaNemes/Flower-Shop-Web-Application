package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.dto.ShoppingCartDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.mapper.ShoppingCartMapper;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.repository.ShoppingCartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     *
     * @param userDto A userDto.
     * @return This method returns the ShoppingCart of the user provided.
     */
    public ShoppingCart getByUser(UserDto userDto){
        log.info("Method getByUser has been called!");
        return shoppingCartRepository.findByUserId(userDto.getId());
    }

    /**
     *
     * @param id The id of a user.
     * @return This method returns the ShoppingCart of the user with the provided id.
     */
    public ShoppingCartDto getByUserId(String id){
        log.info("Method getByUserId has been called!");
        return ShoppingCartMapper.entityToDto(shoppingCartRepository.findByUserId(id));
    }

    /**
     * This method inserts the given ShoppingCart in the database.
     * @param shoppingCart The given ShoppingCart.
     */
    public void addShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
        log.info("ShoppingCart has been successfully added!");
    }

    /**
     * This method updates the given ShoppingCart in the database.
     * @param shoppingCart The given ShoppingCart.
     */
    public void update(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
        log.info("ShoppingCart has been successfully updated!");
    }

    /**
     *
     * @param shoppingCart The given ShoppingCart.
     * @return This method returns the total price of the products that are
     * in the ShoppingCart given as parameter.
     */
    public float computeTotalPrice(ShoppingCart shoppingCart){
        float total = 0;
        List<InCartProduct> inCartProductList = shoppingCart.getProducts();
        for(InCartProduct p: inCartProductList){
            total += p.getPrice()*p.getQuantity();
        }

        log.info("Total price of the ShoppingCart has been calculated!");
        return total;
    }

    /**
     * This method clears the ShoppingCart given as parameter.
     * @param shoppingCart The given ShoppingCart.
     */
    public void emptyShoppingCart(ShoppingCart shoppingCart){
        shoppingCart.setProducts(new ArrayList<>());
        update(shoppingCart);
        log.info("ShoppingCart has been cleared!");
    }

}
