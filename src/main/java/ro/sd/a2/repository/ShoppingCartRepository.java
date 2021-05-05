package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;
import ro.sd.a2.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    ShoppingCart findByUser(User user);
    ShoppingCart findByUserId(String id);
    List<ShoppingCart> findAllByInCartProductsContains(InCartProduct inCartProduct);
}
