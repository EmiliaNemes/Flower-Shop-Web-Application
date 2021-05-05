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
import ro.sd.a2.builder.ProductBuilder;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.User;
import ro.sd.a2.mapper.ProductMapper;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.service.OrderService;
import ro.sd.a2.service.ShoppingCartService;
import ro.sd.a2.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/client")
public class AdminClientController {

    private static final Logger log = LoggerFactory.getLogger(AdminClientController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    /**
     * This method displays all of the users with the Role CLIENT
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="index")
    public String displayIndexUsersForm(Model model){
        List<UserDto> clientUsers = userService.getClients();
        model.addAttribute("title", "Clients");
        model.addAttribute("clients", clientUsers);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        log.info("Clients have been displayed!");
        return "admin/client/view";
    }

    /**
     * This method displays the properties of the client that has to be deleted.
     * @param model
     * @param id The id of the client that has to be removed.
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="remove/{id}", method = RequestMethod.GET)
    public String displayRemoveClientForm(Model model, @PathVariable String id){
        UserDto userDto = userService.getUserById(id);

        model.addAttribute("title", "Delete Client");
        model.addAttribute("client", userDto);
        model.addAttribute("loggedIn", LoginController.loggedUser);
        return "admin/client/remove";
    }

    /**
     * This method firstly deletes the orders of the client with the given id,
     * then deletes the client itself.
     * @param model
     * @param id The id of the client that has to be removed.
     * @return It redirects to the index page.
     */
    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public String processRemoveClientForm(Model model, @PathVariable String id) {
        orderService.deleteOrdersForUser(id);
        userService.removeUser(id);
        log.info("A client has been successfully deleted!");
        return "redirect:../index";
    }
}
