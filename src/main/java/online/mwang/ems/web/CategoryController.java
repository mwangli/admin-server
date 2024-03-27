package online.mwang.ems.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Category;
import online.mwang.ems.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:47
 **/
@RestController
@RequestMapping("/category")
@Api(tags = "用户相关接口")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/list")
    @ApiOperation("查询用户信息")
    ResultInfo<List<Category>> listAll(@RequestBody Category category) {
        return categoryService.listCategory(category);
    }

    @PostMapping("/save")
    @ApiOperation("保存用户信息")
    ResultInfo<Category> saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户信息")
    ResultInfo<Integer> deleteCategory(@RequestBody List<Long> ids) {
        return categoryService.deleteCategory(ids);
    }
}
