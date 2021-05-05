package ro.sd.a2.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sd.a2.builder.UserBuilder;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.User;
import ro.sd.a2.service.OrderService;
import ro.sd.a2.service.ShoppingCartService;
import ro.sd.a2.service.UserService;
import ro.sd.a2.service.VoucherService;

public class UserMapper {

    @Autowired
    private static OrderService orderService;

    @Autowired
    private static ShoppingCartService shoppingCartService;

    public static UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmailAddress(user.getEmailAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public static User dtoToEntity(UserDto userDto) {
        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder
                .setId(userDto.getId())
                .setName(userDto.getName())
                .setEmailAddress(userDto.getEmailAddress())
                .setPhoneNumber(userDto.getPhoneNumber())
                .setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setRole(userDto.getRole())
                .setOrders(orderService.getOrdersByUserId(userDto.getId()))
                .setShoppingCart(ShoppingCartMapper.dtoToEntity(shoppingCartService.getByUserId(userDto.getId())))
                .build();
        return user;
    }
}
