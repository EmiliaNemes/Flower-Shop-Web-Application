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
import ro.sd.a2.dto.ShoppingCartDto;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.Role;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.mapper.ProductMapper;
import ro.sd.a2.mapper.ShoppingCartMapper;
import ro.sd.a2.service.InCartProductService;
import ro.sd.a2.service.ProductService;
import ro.sd.a2.service.ShoppingCartService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("client/flower")
public class ClientFlowerController {

    private static final Logger log = LoggerFactory.getLogger(ClientFlowerController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private InCartProductService inCartProductService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * This method sets the title and the current user of the page.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Flower Shop");
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "client/flower/index";
    }

    /**
     * This method displays all of the products.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="index")
    public String displayIndexFlowerForm(Model model){
        model.addAttribute("title", "Flowers");
        model.addAttribute("flowers", productService.getAllProducts());
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "client/flower/view";
    }

    /**
     * This method displays the properties of the selected product
     * that will be added to be ShoppingCart.
     * @param model
     * @param id The id of the product that will be added to be ShoppingCart.
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="addToCart/{id}", method = RequestMethod.GET)
    public String displayAddToCartForm(Model model, @PathVariable String id){
        ProductDto productDto = productService.getProductById(id);

        model.addAttribute("title", "Add Flower to Shopping Cart");
        model.addAttribute("flower", productDto);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "client/flower/addToCart";
    }

    /**
     * This method adds the selected product to the ShoppingCart of the current user.
     * @param model
     * @param id The id of the selected product.
     * @param productDto The productDto made with the properties of the selected product.
     * @param errors
     * @return It redirects to the main page.
     */
    @RequestMapping(value = "addToCart/{id}", method = RequestMethod.POST)
    public String processAddToCartForm(Model model, @PathVariable String id, @ModelAttribute("flower") @Valid ProductDto productDto, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Flower to Shopping Cart");
            model.addAttribute("loggedIn", LoginController.loggedUser);
            productDto.setId(id);
            log.error("An error has occurred while adding a product to ShoppingCart!");
            return "client/flower/addToCart";
        }

        Product currentProduct = productService.getProduct(id);
        if(currentProduct.getQuantity() < productDto.getQuantity()){
            model.addAttribute("title", "Add Flower to Shopping Cart");
            model.addAttribute("loggedIn", LoginController.loggedUser);
            errors.rejectValue("quantity", "quantity.isBigger", "There is no such quantity of this product! Try less!");
            log.error("The required quantity is too big!");
            return "client/flower/addToCart";
        } else {
            currentProduct.setQuantity(currentProduct.getQuantity() - productDto.getQuantity());
            productService.updateProduct(currentProduct);

            ShoppingCart cart = ShoppingCartMapper.dtoToEntity(shoppingCartService.getByUserId(LoginController.loggedUser.getId()));
            List<InCartProduct> productList = cart.getProducts();

            InCartProduct newInCartProduct = new InCartProductBuilder()
                    .setName(productDto.getName())
                    .setColor(productDto.getColor())
                    .setCategory(productDto.getCategory())
                    .setPrice(productDto.getPrice())
                    .setQuantity(productDto.getQuantity())
                    .setOrdered(false)
                    .setShoppingCart(cart)
                    .build();

            inCartProductService.add(newInCartProduct);
            productList.add(newInCartProduct);
            cart.setProducts(productList);
            shoppingCartService.update(cart);
            model.addAttribute("loggedIn", LoginController.loggedUser);
            log.info("Product has been successfully added to ShoppingCart!");
            return "redirect:../index";
        }
    }

}
