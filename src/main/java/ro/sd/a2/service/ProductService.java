package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.dto.ShoppingCartDto;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.exceptions.*;
import ro.sd.a2.mapper.ProductMapper;
import ro.sd.a2.mapper.ShoppingCartMapper;
import ro.sd.a2.repository.ProductRepository;
import ro.sd.a2.validators.ProductValidator;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * This method adds a product to the database.
     * @param product The product that has to be inserted.
     */
    public void addProduct(Product product) {
        productRepository.save(product);
        log.info("Product has been successfully added!");
    }

    /**
     *
     * @return This method returns all of the products that are stored in the database.
     */
    public List<ProductDto> getAllProducts(){
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product p: productRepository.findAll()){
            productDtoList.add(ProductMapper.entityToDto(p));
        }
        log.info("Products has been successfully returned!");
        return productDtoList;
    }

    /**
     *
     * @param id The id of a product.
     * @return This method returns the ProductDto with the given id.
     */
    public ProductDto getProductById(String id){
        log.info("Method getProductById has been called!");
       return ProductMapper.entityToDto(productRepository.getOne(id));
    }

    /**
     *
     * @param id The id of a product.
     * @return This method returns the Product with the given id.
     */
    public Product getProduct(String id){
        log.info("Method getProduct has been called!");
        return productRepository.getOne(id);
    }

    /**
     * This method deletes from the database the Product with the given id.
     * @param id The id of a product.
     */
    public void deleteProductById(String id){
        productRepository.deleteById(id);
        log.info("Product has been successfully deleted!");
    }

    /**
     * This method updates in the database the product provided as parameter.
     * @param product The given product.
     */
    public void updateProduct(Product product){
        productRepository.save(product);
        log.info("Product has been successfully updated!");
    }

    /**
     *
     * @param name Name of the product.
     * @param color Color of the product.
     * @param price Price of the product.
     * @return This method returns the product which has the given name, color and price.
     */
    public Product getByNameAndColorAndPrice(String name, String color, float price){
        log.info("Method getByNameAndColorAndPrice has been called!");
        return productRepository.findByNameAndColorAndPrice(name, color, price);
    }

}
