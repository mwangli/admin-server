package online.mwang.ems.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Favorite;
import online.mwang.ems.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:47
 **/
@RestController
@RequestMapping("/favorite")
@Api(tags = "收藏相关接口")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/save")
    @ApiOperation("保存收藏信息")
    ResultInfo<Favorite> saveFavorite(@RequestBody Favorite favorite) {
        return favoriteService.saveFavorite(favorite);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除收藏信息")
    ResultInfo<Integer> deleteFavorite(@RequestBody List<Long> ids) {
        return favoriteService.deleteFavorite(ids);
    }
}
