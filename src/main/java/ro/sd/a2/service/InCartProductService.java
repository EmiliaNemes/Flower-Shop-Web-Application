package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.mapper.ProductMapper;
import ro.sd.a2.repository.InCartProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class InCartProductService {

    private static final Logger log = LoggerFactory.getLogger(InCartProductService.class);

    @Autowired
    private InCartProductRepository inCartProductRepository;

    /**
     * This method adds an InCartProduct to the database.
     * @param inCartProduct The InCartProduct that will be added to the database.
     */
    public void add(InCartProduct inCartProduct){
        inCartProductRepository.save(inCartProduct);
        log.info("InCartProduct has been successfully added!");
    }

    /**
     * This method dletes an InCartProduct from the database.
     * @param inCartProduct The InCartProduct that will be deleted from the database.
     */
    public void delete(InCartProduct inCartProduct){
        inCartProductRepository.delete(inCartProduct);
        log.info("InCartProduct has been successfully deleted!");
    }

    /**
     *
     * @param shoppingCart The given ShoppingCart.
     * @return This method returns the products of the ShoppingCart that is given as parameter.
     */
    public List<InCartProduct> getProductsInShoppingCart(ShoppingCart shoppingCart){
        List<InCartProduct> inCartProductList = new ArrayList<>();
        for(InCartProduct p: inCartProductRepository.findByShoppingCart(shoppingCart)){
            if(!p.isOrdered()){
                inCartProductList.add(p);
            }
        }
        log.info("The products of a shopping cart has been returned!");
        return inCartProductList;
    }

    /**
     *
     * @param id The id of the InCartProduct.
     * @return This method returns the InCartProduct corresponding to the given id.
     */
    public InCartProduct getInCartProductById(String id){
        log.info("The method getInCartProductById has been called!");
        return inCartProductRepository.getOne(id);
    }

    /**
     * This method updates the InCartProduct given as parameter.
     * @param inCartProduct The given InCartProduct.
     */
    public void update(InCartProduct inCartProduct){
        inCartProductRepository.save(inCartProduct);
        log.info("InCartProduct has been successfully updated!");
    }
}
