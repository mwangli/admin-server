package online.mwang.ems.repo;

import online.mwang.ems.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mwangli
 * @date 2020/12/17 16:50
 **/
public interface UserRepo extends JpaRepository<User,Long> {
}
