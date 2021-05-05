package ro.sd.a2.dto;

import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private String id;
    private float totalPrice;
    private LocalDate date;
    private List<InCartProduct> inCartProducts;
    private UserDto userDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<InCartProduct> getInCartProducts() {
        return inCartProducts;
    }

    public void setInCartProducts(List<InCartProduct> inCartProducts) {
        this.inCartProducts = inCartProducts;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
