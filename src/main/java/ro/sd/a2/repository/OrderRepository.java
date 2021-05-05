package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.InCartProduct;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
    List<Order> findByUserId(String id) throws NullPointerException;
    List<Order> findAllByInCartProductsContains(InCartProduct product);

}
