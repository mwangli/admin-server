package online.mwang.ems.repo;

import online.mwang.ems.pojo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mwangli
 * @date 2020/12/15 9:25
 **/
public interface ProductRepo extends JpaRepository<Product, Long> {

}
