package online.mwang.ems.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Product;
import online.mwang.ems.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:47
 **/
@RestController
@RequestMapping("/product")
@Api(tags = "商品相关接口")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/list")
    @ApiOperation("查询商品信息")
    ResultInfo<List<Product>> listAll(@RequestBody Product product) {
        return productService.listProduct(product);
    }

    @PostMapping("/save")
    @ApiOperation("保存商品信息")
    ResultInfo<Product> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除商品信息")
    ResultInfo<Integer> deleteProduct(@RequestBody List<Long> ids) {
        return productService.deleteProduct(ids);
    }
}
