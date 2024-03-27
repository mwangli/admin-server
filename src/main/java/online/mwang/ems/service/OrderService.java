package online.mwang.ems.service;

import online.mwang.ems.commom.enums.StatusEnum;
import online.mwang.ems.commom.utils.UpdateUtil;
import online.mwang.ems.pojo.bean.PageInfo;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Order;
import online.mwang.ems.repo.OrderRepo;
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
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public ResultInfo<List<Order>> listOrder(Order order) {
        order.setStatus(StatusEnum.AVAILABLE.getIndex());
        Integer pageIndex = Optional.ofNullable(order.getPageIndex()).orElse(1);
        Integer pageSize = Optional.ofNullable(order.getPageSize()).orElse(10);
        Integer total = (int) orderRepo.count(Example.of(order));
        List<Order> orderList = orderRepo.findAll(Example.of(order), Sort.by(Sort.Order.desc("createTime")))
                .parallelStream().skip((pageIndex - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return ResultInfo.success(orderList, PageInfo.of(pageIndex, pageSize, total));
    }

    public ResultInfo<Order> saveOrder(Order order) {
        if (order.getId() != null && orderRepo.existsById(order.getId())) {
            Order order1 = orderRepo.findById(order.getId()).get();
            UpdateUtil.copyNullProperties(order1, order);
        } else {
            order.setCreateTime(new Date());
        }
        order.setModifyTime(new Date());
        return ResultInfo.success(orderRepo.save(order));
    }

    public ResultInfo<Integer> deleteOrder(List<Long> ids) {
        AtomicInteger count = new AtomicInteger();
        ids.forEach(id ->
                orderRepo.findById(id).ifPresent(order -> {
                    order.setStatus(StatusEnum.DELETED.getIndex());
                    orderRepo.save(order);
                    count.getAndIncrement();
                }));
        return ResultInfo.success(count.get());
    }
}
