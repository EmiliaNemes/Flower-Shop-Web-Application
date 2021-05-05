package ro.sd.a2.validators;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.service.UserService;

import javax.persistence.NoResultException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    @Autowired
    private static UserService userService;

    public static boolean isUserValid(String username){
        try{
            userService.getUserByUsername(username);
            return true;
        } catch(NoResultException e){
            return false;
        }
    }

    public static boolean isNameValid(String name){
        //return name != null && !name.equals("") && name.matches("([A-Z][a-z]*[ ])*");
        return name != null && !name.equals("") && name.matches("[A-Z][a-z]*");
    }

    public static boolean isEmailValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isPhoneNumberValid(String phoneNumber){
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public static boolean isUsernameValid(String username){
        if(username == null || username.equals("")){
            return false;
        } else {
            try{
                userService.getUserByUsername(username);
                return false;
            } catch(NoResultException | NullPointerException e){
                return true;
            }
        }
    }

    public static boolean arePasswordsMatching(String password1, String password2){
        return password1.equals(password2);
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() < 5) {
            return false;
        } else {
            boolean containsLetter = false;
            boolean containsDigit = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (Character.isLetter(c)) containsLetter = true;
                if (Character.isDigit(c)) containsDigit = true;
            }
            return containsLetter & containsDigit;
        }
    }

}
