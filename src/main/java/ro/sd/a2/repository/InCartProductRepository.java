package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.ShoppingCart;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface InCartProductRepository extends JpaRepository<InCartProduct, String> {

    List<InCartProduct> findByShoppingCart(ShoppingCart shoppingCart);
    InCartProduct findByNameAndColorAndPrice(String name, String color, float price);

}
