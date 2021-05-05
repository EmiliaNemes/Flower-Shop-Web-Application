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
import ro.sd.a2.dto.LoginUserDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.Role;
import ro.sd.a2.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    protected static UserDto loggedUser;

    /**
     * This method displays the title and the current user.
     * @param model
     * @return It returns the corresponding html page.
     */
    @RequestMapping(value="", method = RequestMethod.GET)
    public String displayLoginForm(Model model){
        model.addAttribute("title", "Login to FlowerShop");
        model.addAttribute("loginUser", new LoginUserDto());
        return "login";
    }

    /**
     * This method displays a form where the users can introduce their username and password.
     * @param loginUser The object made with the provided data.
     * @param result
     * @param errors
     * @param model
     * @return It redirects to the main page of the clients or admins depending on the role of
     * the user that has logged in.
     */
    @RequestMapping(value="", method = RequestMethod.POST)
    public String processLoginForm(@Valid @ModelAttribute("loginUser") LoginUserDto loginUser, BindingResult result, Errors errors, Model model){

        if (result.hasErrors()) {
            model.addAttribute("title", "Login to FlowerShop");
            log.error("An error has occurred while logging in!");
            return "login";
        }

        try{
            loggedUser = userService.getUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
            if (loggedUser.getRole().equals(Role.CLIENT)) {
                log.info("A client has been logged in!");
                return "redirect:client/flower";
            } else {
                log.info("An administrator has been logged in!");
                return "redirect:admin/flower";
            }
        } catch(NullPointerException e) {
            model.addAttribute("title", "Login to FlowerShop");
            errors.rejectValue("password", "username.exists", "Username OR password is incorrect!");
            log.error("There is no user with the given username and password!!");
            return "login";
        }
    }
}
