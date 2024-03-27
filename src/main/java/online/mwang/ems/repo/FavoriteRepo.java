package online.mwang.ems.repo;

import online.mwang.ems.pojo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mwangli
 * @date 2020/12/17 17:47
 **/
public interface FavoriteRepo extends JpaRepository<Favorite,Long> {
}
