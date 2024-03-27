package online.mwang.ems.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.mwang.ems.pojo.bean.ResultInfo;
import online.mwang.ems.pojo.entity.Order;
import online.mwang.ems.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author mwangli
 * @Date 2020/12/11 15:47
 **/
@RestController
@RequestMapping("/order")
@Api(tags = "订单相关接口")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/list")
    @ApiOperation("查询订单信息")
    ResultInfo<List<Order>> listAll(@RequestBody Order order) {
        return orderService.listOrder(order);
    }

    @PostMapping("/save")
    @ApiOperation("保存订单信息")
    ResultInfo<Order> saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单信息")
    ResultInfo<Integer> deleteOrder(@RequestBody List<Long> ids) {
        return orderService.deleteOrder(ids);
    }
}
