package online.mwang.ems.service;

import online.mwang.ems.commom.enums.StatusEnum;
import online.mwang.ems.commom.utils.UpdateUtil;
import online.mwang.ems.pojo.bean.PageInfo;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Category;
import online.mwang.ems.repo.CategoryRepo;
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
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public ResultInfo<List<Category>> listCategory(Category category) {
        category.setStatus(StatusEnum.AVAILABLE.getIndex());
        Integer pageIndex = Optional.ofNullable(category.getPageIndex()).orElse(1);
        Integer pageSize = Optional.ofNullable(category.getPageSize()).orElse(10);
        Integer total = (int) categoryRepo.count(Example.of(category));
        List<Category> categoryList = categoryRepo.findAll(Example.of(category), Sort.by(Sort.Order.desc("createTime")))
                .parallelStream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return ResultInfo.success(categoryList, PageInfo.of(pageIndex, pageSize, total));
    }

    public ResultInfo<Category> saveCategory(Category category) {
        if (category.getId() != null && categoryRepo.existsById(category.getId())) {
            Category category1 = categoryRepo.findById(category.getId()).get();
            UpdateUtil.copyNullProperties(category1, category);
        } else {
            category.setCreateTime(new Date());
        }
        category.setModifyTime(new Date());
        return ResultInfo.success(categoryRepo.save(category));
    }

    public ResultInfo<Integer> deleteCategory(List<Long> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(id ->
                categoryRepo.findById(id).ifPresent(category -> {
                    category.setStatus(StatusEnum.DELETED.getIndex());
                    categoryRepo.save(category);
                    count.getAndIncrement();
                }));
        return ResultInfo.success(count.get());
    }
}
