package cn.itcast.core.controller;

import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.OrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: pyg_parent
 * @author: 刘家兴
 * @create: 2019-03-06 19:05
 * @version: 1.0
 **/
@RestController
@RequestMapping("order")
public class OrderController {

    @Reference
    private OrderService orderService;


    /**
     * 查询订单集合
     *
     * @return
     */
    @RequestMapping("getOrderList")
    public List<Order> getOrderList() {
        List<Order> orderList = orderService.getOrderList();
        return orderList;
    }
}
