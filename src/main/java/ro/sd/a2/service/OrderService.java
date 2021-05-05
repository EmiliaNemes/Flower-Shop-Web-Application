package ro.sd.a2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.OrderDto;
import ro.sd.a2.dto.UserDto;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.User;
import ro.sd.a2.exceptions.CustomExceptionMessages;
import ro.sd.a2.exceptions.InvalidUserException;
import ro.sd.a2.mapper.OrderMapper;
import ro.sd.a2.mapper.UserMapper;
import ro.sd.a2.repository.OrderRepository;
import ro.sd.a2.validators.UserValidator;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    /**
     * This method adds an order to the database.
     * @param order The order that has to be inserted.
     */
    public void addOrder(Order order){
        orderRepository.save(order);
        log.info("Order has been successfully added!");
    }

    /**
     *
     * @param id The id of a user.
     * @return This method return the orders made by the user with the given id.
     */
    public List<Order> getOrdersByUserId(String id){
        try {
            List<Order> orders = orderRepository.findByUserId(id);
            if(orders == null){
                log.info("User has no orders yet!");
                return new ArrayList<>();
            }
            log.info("Users orders have been returned!");
            return orders;
        }catch (NullPointerException e){
            log.info("User has no orders yet!");
            return new ArrayList<>();
        }
    }

    /**
     * This method deletes the orders made by the user with the given id.
     * @param id The id of a user.
     */
    public void deleteOrdersForUser(String id){
        List<Order> orders = orderRepository.findByUserId(id);
        for (Order order: orders) {
            orderRepository.delete(order);
        }
        log.info("Users orders have been successfully deleted!");
    }

    /**
     *
     * @param userDto The user whose orders has to be returned.
     * @return This method return the orders made by the given user.
     */
    public List<Order> getOrderForUser(UserDto userDto){
        log.info("Method getOrderForUser has been called!");
        return orderRepository.findByUserId(userDto.getId());
    }
}
