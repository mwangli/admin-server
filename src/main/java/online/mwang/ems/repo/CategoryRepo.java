package online.mwang.ems.repo;

import online.mwang.ems.pojo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mwangli
 * @date 2020/12/17 17:34
 **/
public interface CategoryRepo extends JpaRepository<Category,Long> {
}
