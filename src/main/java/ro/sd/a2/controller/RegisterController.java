package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.sd.a2.builder.ShoppingCartBuilder;
import ro.sd.a2.builder.UserBuilder;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Role;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.entity.User;
import ro.sd.a2.exceptions.*;
import ro.sd.a2.mapper.ShoppingCartMapper;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.ShoppingCartService;
import ro.sd.a2.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("register")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * This method displays a form where the users can introduce data for registration.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public String displayRegisterForm(Model model){
        model.addAttribute("title", "Registration");
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", Role.values());
        return "register";
    }

    /**
     * This method creates a user based on the provided data.
     * @param userDto The object made with the provided data.
     * @param result
     * @param errors
     * @param model
     * @return It redirects to the login page.
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public String processRegisterForm(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Errors errors, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("title", "Registration");
            model.addAttribute("roles", Role.values());
            log.error("An error has occurred while registration!");
            return "register";
        }

        try{
            userService.getUserByUsername(userDto.getUsername());
            model.addAttribute("title", "Registration");
            model.addAttribute("roles", Role.values());
            errors.rejectValue("username", "username.exists", "A user with this username already exists!");
            log.error("A user with this username already exists!");
            return "register";

        } catch(NullPointerException e){
            ShoppingCart shoppingCart = new ShoppingCartBuilder()
                    .setProducts(new ArrayList<>())
                    .build();
            shoppingCartService.addShoppingCart(shoppingCart);
            
            List<Order> orders = new ArrayList<>();

            User newUser = new UserBuilder()
                    .setId(userDto.getId())
                    .setName(userDto.getName())
                    .setEmailAddress(userDto.getEmailAddress())
                    .setPhoneNumber(userDto.getPhoneNumber())
                    .setUsername(userDto.getUsername())
                    .setPassword(userDto.getPassword())
                    .setRole(userDto.getRole())
                    .setOrders(orders)
                    .setShoppingCart(shoppingCart)
                    .build();
            userService.addUser(newUser);
            shoppingCart.setUser(newUser);
            shoppingCartService.update(shoppingCart);

            log.info("A new user has successfully registered!");
            return "redirect:/login";
        }
    }
}
