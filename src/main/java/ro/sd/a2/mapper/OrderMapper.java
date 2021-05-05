package ro.sd.a2.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sd.a2.builder.OrderBuilder;
import ro.sd.a2.dto.OrderDto;
import ro.sd.a2.entity.Order;
import ro.sd.a2.service.VoucherService;

public class OrderMapper {

    @Autowired
    private static VoucherService voucherService;

    public static OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserDto(UserMapper.entityToDto(order.getUser()));
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setDate(order.getDate());
        orderDto.setInCartProducts(order.getProducts());
        return orderDto;
    }

    public static Order dtoToEntity(OrderDto orderDto){
        Order order = new OrderBuilder()
            .setId(orderDto.getId())
            .setUser(UserMapper.dtoToEntity(orderDto.getUserDto()))
            .setTotalPrice(orderDto.getTotalPrice())
            .setDate(orderDto.getDate())
                .setInCartProducts(orderDto.getInCartProducts())
                .build();
        return order;
    }

}
