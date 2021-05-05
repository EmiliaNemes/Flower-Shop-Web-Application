package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ro.sd.a2.builder.ProductBuilder;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.Category;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.service.OrderService;
import ro.sd.a2.service.ProductService;
import ro.sd.a2.service.ShoppingCartService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/flower")
public class AdminFlowerController {

    private static final Logger log = LoggerFactory.getLogger(AdminFlowerController.class);

    @Autowired
    private ProductService productService;


    /**
     * This method sets the current user and the title of the page.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value ="")
    public String index(Model model){
        model.addAttribute("title", "Flower Shop");
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/flower/index";
    }

    /**
     * This method displays all the products.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="index")
    public String displayIndexFlowerForm(Model model){
        model.addAttribute("title", "Flowers");
        model.addAttribute("flowers", productService.getAllProducts());
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/flower/view";
    }

    /**
     * This method displays a form to be completed in order to add a new Flower.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddFlowerForm(Model model){
        model.addAttribute("title", "Add Flower");
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("flowerCategories", Category.values());
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/flower/add";
    }

    /**
     * This method creates a new Flower with the data that have been introduced.
     * @param productDto There is made a productDto with the data that have been introduced.
     * @param result
     * @param errors
     * @param model
     * @return It redirects to the main page.
     */
    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddFlowerForm(@ModelAttribute @Valid ProductDto productDto, BindingResult result, Errors errors, Model model) {

        if(result.hasErrors()){
            model.addAttribute("title", "Add Flower");
            model.addAttribute("loggedIn", LoginController.loggedUser);
            model.addAttribute("flowerCategories", Category.values());
            log.error("An error has occurred while adding a Flower!");
            return "admin/flower/add";
        }

        Product newProduct = new ProductBuilder()
                .setName(productDto.getName())
                .setColor(productDto.getColor())
                .setCategory(productDto.getCategory())
                .setPrice(productDto.getPrice())
                .setQuantity(productDto.getQuantity())
                .build();
        productService.addProduct(newProduct);
        log.info("Flower has been successfully added!");
        return "redirect:";
    }

    /**
     * This method displays all the products, and the one(s) that want to deleted has to be selected.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String displayRemoveFlowerForm(Model model){
        model.addAttribute("title", "Delete Flower");
        model.addAttribute("flowers", productService.getAllProducts());
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/flower/remove";
    }

    /**
     * This method removes the selected products.
     * @param model
     * @param ids The ids of the selected products.
     * @return It redirects to the main page.
     */
    @RequestMapping(value = "remove", method = RequestMethod.POST)
        public String processRemoveFlowerForm(Model model, @RequestParam(required = false) String[] ids) {
            if (ids == null){
                model.addAttribute("flowers", productService.getAllProducts());
                model.addAttribute("title", "Delete Flower");
                model.addAttribute("loggedIn", LoginController.loggedUser);
                log.warn("No Flower has been selected on delete!");
                return "admin/flower/remove";
            }

            for (String id : ids) {
                productService.deleteProductById(id);
            }

        log.info("Flower(s) has been successfully deleted!");
            return "redirect:/admin/flower";
        }

    /**
     * This method displays the properties of the product that has to be edited.
     * @param model
     * @param id The id of the edited product.
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="update/{id}", method = RequestMethod.GET)
    public String displayUpdateFlowerForm(Model model, @PathVariable String id){
        ProductDto productDto = productService.getProductById(id);

        model.addAttribute("title", "Edit Flower");
        model.addAttribute("flower", productDto);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/flower/update";
    }

    /**
     * This method updates the product based on the introduced data.
     * @param id The id of the updated product.
     * @param productDto The productDto made with the introduced properties.
     * @param result
     * @param errors
     * @param model
     * @return It redirects to the main page.
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String processUpdateFlowerForm(@PathVariable String id, @ModelAttribute("flower") @Valid ProductDto productDto, BindingResult result, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Flower");
            model.addAttribute("loggedIn", LoginController.loggedUser);
            productDto.setId(id);
            log.error("An error has occurred while updating a Flower!");
            return "admin/flower/update";
        }

        Product updatedProduct = new ProductBuilder()
                .setId(productDto.getId())
                .setName(productDto.getName())
                .setColor(productDto.getColor())
                .setCategory(productDto.getCategory())
                .setPrice(productDto.getPrice())
                .setQuantity(productDto.getQuantity())
                .build();
        productService.updateProduct(updatedProduct);

        model.addAttribute("flowers", productService.getAllProducts());
        model.addAttribute("loggedIn", LoginController.loggedUser);
        log.info("Flower has been successfully updated!");
        return "redirect:../index";
    }
}