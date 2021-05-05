package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Order;
import ro.sd.a2.entity.User;
import ro.sd.a2.entity.Voucher;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface VoucherRepository extends JpaRepository<Voucher, String> {

    List<Voucher> findByUser(User user);

    List<Voucher> findByOrder(Order order);
}
