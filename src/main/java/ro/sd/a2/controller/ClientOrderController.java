package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ro.sd.a2.builder.OrderBuilder;
import ro.sd.a2.builder.UserBuilder;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.InCartProductService;
import ro.sd.a2.service.OrderService;
import ro.sd.a2.service.ShoppingCartService;
import ro.sd.a2.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("client/order")
public class ClientOrderController {

    private static final Logger log = LoggerFactory.getLogger(ClientOrderController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private InCartProductService inCartProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /**
     * This method displays the orders of the current user.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value = "view")
    public String index(Model model){
        model.addAttribute("title", "My Orders");
        model.addAttribute("loggedIn", LoginController.loggedUser);
        model.addAttribute("orders", orderService.getOrderForUser(LoginController.loggedUser));
        return "client/order/view";
    }

    /**
     * This method displays the products that are in the ShoppingCart
     * and will be in the order.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddOrderForm(Model model) {
        model.addAttribute("title", "Order");
        model.addAttribute("loggedIn", LoginController.loggedUser);

        ShoppingCart shoppingCart = shoppingCartService.getByUser(LoginController.loggedUser);
        model.addAttribute("products", inCartProductService.getProductsInShoppingCart(shoppingCart));
        return "client/order/add";
    }

    /**
     * This method makes an order for the current user with the products
     * that are currently in the ShopppingCart, then clears the ShopppingCart.
     * @param model
     * @return It redirects to the page which displays the finalized orders.
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddOrderForm(Model model) {
        ShoppingCart shoppingCart = shoppingCartService.getByUser(LoginController.loggedUser);

        List<InCartProduct> currentProducts = new ArrayList<>();
        currentProducts.addAll(shoppingCart.getProducts());

        Order order = new OrderBuilder()
                .setUser(userService.getUser(LoginController.loggedUser.getId()))
                .setTotalPrice(shoppingCartService.computeTotalPrice(shoppingCart))
                .setInCartProducts(currentProducts)
                .setDate(LocalDate.now())
                .build();

        List<Order> orderList = userService.getUser(LoginController.loggedUser.getId()).getOrders();
        orderList.add(order);
        userService.getUser(LoginController.loggedUser.getId()).setOrders(orderList);

        orderService.addOrder(order);
        shoppingCartService.emptyShoppingCart(shoppingCart);
        userService.updateUser(LoginController.loggedUser);

        model.addAttribute("title", "Order");
        model.addAttribute("loggedIn", LoginController.loggedUser);
        log.info("Order has been successfully placed!");
        return "redirect:../order/view";
    }
}
