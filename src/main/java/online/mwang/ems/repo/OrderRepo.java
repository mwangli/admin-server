package online.mwang.ems.repo;

import online.mwang.ems.pojo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mwangli
 * @date 2020/12/17 14:56
 **/
public interface OrderRepo extends JpaRepository<Order,Long> {
}
