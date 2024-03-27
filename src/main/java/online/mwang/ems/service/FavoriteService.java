package online.mwang.ems.service;

import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Favorite;
import online.mwang.ems.repo.FavoriteRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:55
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class FavoriteService {

    private final FavoriteRepo favoriteRepo;

    public FavoriteService(FavoriteRepo favoriteRepo) {
        this.favoriteRepo = favoriteRepo;
    }


    public ResultInfo<Favorite> saveFavorite(Favorite favorite) {
        return ResultInfo.success(favoriteRepo.save(favorite));
    }

    public ResultInfo<Integer> deleteFavorite(List<Long> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(id -> {
            favoriteRepo.deleteById(id);
            count.getAndIncrement();
        });
        return ResultInfo.success(count.get());
    }
}
