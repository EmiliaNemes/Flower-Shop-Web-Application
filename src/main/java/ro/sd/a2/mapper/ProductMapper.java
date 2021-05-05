package ro.sd.a2.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ro.sd.a2.builder.ProductBuilder;
import ro.sd.a2.dto.ProductDto;
import ro.sd.a2.entity.Product;
import ro.sd.a2.service.OrderService;
import ro.sd.a2.service.ProductService;
import ro.sd.a2.service.ShoppingCartService;

public class ProductMapper {

    @Autowired
    private static OrderService orderService;

    @Autowired
    private static ShoppingCartService shoppingCartService;

    @Autowired
    private static ProductService productService;

    public static ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setColor(product.getColor());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto){
        ProductBuilder productBuilder = new ProductBuilder();
        Product product = productBuilder
                .setId(productDto.getId())
                .setName(productDto.getName())
                .setCategory(productDto.getCategory())
                .setColor(productDto.getColor())
                .setPrice(productDto.getPrice())
                .setQuantity(productDto.getQuantity())
                .build();
        return product;
    }
}
