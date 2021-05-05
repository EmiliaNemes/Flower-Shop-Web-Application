package ro.sd.a2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.sd.a2.builder.InCartProductBuilder;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.mapper.ProductMapper;
import ro.sd.a2.service.InCartProductService;
import ro.sd.a2.service.ProductService;
import ro.sd.a2.service.ShoppingCartService;

import javax.validation.Valid;

@Controller
@RequestMapping("client/shopping_cart")
public class ClientShoppingCartController {

    private static final Logger log = LoggerFactory.getLogger(ClientShoppingCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private InCartProductService inCartProductService;

    @Autowired
    private ProductService productService;

    /**
     * This method displays the products that are is the ShoppingCart.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value = "view")
    public String index(Model model) {
        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("loggedIn", LoginController.loggedUser);

        ShoppingCart shoppingCart = shoppingCartService.getByUser(LoginController.loggedUser);
        model.addAttribute("products", inCartProductService.getProductsInShoppingCart(shoppingCart));
        return "client/shopping_cart/view";
    }

    /**
     * This method displays the properties of the product that was chosen to be removed.
     * @param model
     * @param id
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="remove/{id}", method = RequestMethod.GET)
    public String displayRemoveFlowerForm(Model model, @PathVariable String id){
        InCartProduct inCartProduct = inCartProductService.getInCartProductById(id);

        model.addAttribute("title", "Remove Flower from Shopping Cart");
        model.addAttribute("flower", inCartProduct);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "client/shopping_cart/remove";
    }

    /**
     * This method removes the product with the given id.
     * @param model
     * @param id The id of the product that has to be deleted.
     * @return It redirects to the page which displays the content of the ShoppingCart.
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public String processRemoveFlowerForm(Model model, @PathVariable String id) {
        InCartProduct inCartProduct = inCartProductService.getInCartProductById(id);

        Product product = productService.getByNameAndColorAndPrice(inCartProduct.getName(), inCartProduct.getColor(), inCartProduct.getPrice());
        product.setQuantity(product.getQuantity() + inCartProduct.getQuantity());
        productService.updateProduct(product);
        inCartProductService.delete(inCartProduct);
        log.info("Product has been successfully removed from ShoppingCart!");
        return "redirect:/client/shopping_cart/view";
    }

    /**
     * This method displays the properties of the product that was chosen to be updated.
     * @param model
     * @param id The id of the product that has to be updated.
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="update/{id}", method = RequestMethod.GET)
    public String displayUpdateFlowerForm(Model model, @PathVariable String id){
        InCartProduct inCartProduct = inCartProductService.getInCartProductById(id);

        model.addAttribute("title", "Edit Flower in Shopping Cart");
        model.addAttribute("flower", inCartProduct);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "client/shopping_cart/update";
    }

    /**
     * This method updates the product with the given id.
     * @param model
     * @param id The id of the product that has to be updated.
     * @param productDto The productDto made with the properties of the selected product.
     * @param errors
     * @return It redirects to the page which displays the content of the ShoppingCart.
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String processUpdateFlowerForm(Model model, @PathVariable String id, @ModelAttribute("flower") @Valid ProductDto productDto, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Flower in Shopping Cart");
            model.addAttribute("loggedIn", LoginController.loggedUser);
            productDto.setId(id);
            log.error("An error has occurred while updating a product in ShoppingCart!");
            return "client/shopping_cart/update";
        }

        Product product = productService.getByNameAndColorAndPrice(productDto.getName(), productDto.getColor(), productDto.getPrice());
        InCartProduct currentInCartProduct = inCartProductService.getInCartProductById(id);

        if(currentInCartProduct.getQuantity() > productDto.getQuantity()){
            product.setQuantity(product.getQuantity() + (currentInCartProduct.getQuantity() - productDto.getQuantity()));
            currentInCartProduct.setQuantity(productDto.getQuantity());
        } else {
            if(product.getQuantity() < (productDto.getQuantity() - currentInCartProduct.getQuantity())){
                model.addAttribute("title", "Edit Flower in Shopping Cart");
                model.addAttribute("loggedIn", LoginController.loggedUser);
                errors.rejectValue("quantity", "quantity.isBigger", "There is no such quantity of this product! Try less!");
                log.error("The required quantity is too big!");
                return "client/shopping_cart/update";
            } else {
                product.setQuantity(product.getQuantity() - (productDto.getQuantity() - currentInCartProduct.getQuantity()));
                currentInCartProduct.setQuantity(productDto.getQuantity());
            }
        }

        inCartProductService.update(currentInCartProduct);
        productService.updateProduct(product);

        model.addAttribute("flowers", productService.getAllProducts());
        model.addAttribute("loggedIn", LoginController.loggedUser);

        log.info("Product has been successfully updated in ShoppingCart!");
        return "redirect:/client/shopping_cart/view";
    }

}
