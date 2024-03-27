package online.mwang.ems.service;

import online.mwang.ems.commom.enums.StatusEnum;
import online.mwang.ems.commom.utils.UpdateUtil;
import online.mwang.ems.pojo.bean.PageInfo;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Product;
import online.mwang.ems.repo.ProductRepo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:55
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public ResultInfo<List<Product>> listProduct(Product product) {
        product.setStatus(StatusEnum.AVAILABLE.getIndex());
        Integer pageIndex = Optional.ofNullable(product.getPageIndex()).orElse(1);
        Integer pageSize = Optional.ofNullable(product.getPageSize()).orElse(10);
        Integer total = (int) productRepo.count(Example.of(product));
        List<Product> productList = productRepo.findAll(Example.of(product), Sort.by(Sort.Order.desc("createTime")))
                .parallelStream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return ResultInfo.success(productList, PageInfo.of(pageIndex, pageSize, total));
    }

    public ResultInfo<Product> saveProduct(Product product) {
        if (product.getId() != null && productRepo.existsById(product.getId())) {
            Product product1 = productRepo.findById(product.getId()).get();
            UpdateUtil.copyNullProperties(product1, product);
        } else {
            product.setCreateTime(new Date());
        }
        product.setModifyTime(new Date());
        return ResultInfo.success(productRepo.save(product));
    }

    public ResultInfo<Integer> deleteProduct(List<Long> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(id ->
                productRepo.findById(id).ifPresent(product -> {
                    product.setStatus(StatusEnum.DELETED.getIndex());
                    productRepo.save(product);
                    count.getAndIncrement();
                }));
        return ResultInfo.success(count.get());
    }
}
