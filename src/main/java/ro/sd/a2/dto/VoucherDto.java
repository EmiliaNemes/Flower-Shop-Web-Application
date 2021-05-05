package ro.sd.a2.dto;

import java.time.LocalDate;

public class VoucherDto {

    private String id;
    private UserDto userDto;
    private OrderDto orderDto;
    private float value;
    private String category;
    private LocalDate expirationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDto getUserDto() { return userDto; }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
