package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.EchrtsResult;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.service.ManagerOrdersService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Reference
    private ManagerOrdersService managerOrdersService;

    /**
     * 后端订单分页显示
     *
     * @param page  当前页码
     * @param rows  显示数量
     * @param order
     * @return 返回分页订单数据
     */
    @RequestMapping("search")
    public PageResult search(Integer page, Integer rows, @RequestBody Order order) {
        Map<List<String>, List<String>> listMap;
        return managerOrdersService.findPage(page, rows, order);
    }

    /**
     * 查询每日销量折线图的数据
     * @return
     */
    @RequestMapping("/echarts")
    public List<EchrtsResult> echarts() {
        return managerOrdersService.findech();
    }

    /**
     * 查询订单数量折线图数据
     * @return
     */
    @RequestMapping("/echarts2")
    public List<OrderItem> echarts2() {
        return managerOrdersService.findechZX();
    }

    /**
     * 查询订单分类销售饼状图
     */
    @RequestMapping("/echarts3")
    public List<OrderItem> echarts3() {
        return managerOrdersService.findechB();
    }

}
