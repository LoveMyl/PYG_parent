package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.OrderService;
import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.security.core.context.SecurityContextHolder;
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
     * 分页查询 订单数据
     *
     * @param page 当前页
     * @param rows 每页显示个数
     * @return
     */
    @RequestMapping("search")
    public PageResult search(Integer page, Integer rows) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        PageResult pageResult = orderService.search(page,rows,userName);
        return pageResult;
    }
}
