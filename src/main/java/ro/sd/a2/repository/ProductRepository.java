package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Product;
import ro.sd.a2.entity.ShoppingCart;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, String> {

    Product findByNameAndColorAndPrice(String name, String color, float price);
}
