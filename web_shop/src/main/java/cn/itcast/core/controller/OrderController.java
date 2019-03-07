package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.ShopOrdersService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("order")
public class OrderController {

    @Reference
    private ShopOrdersService shopOrdersService;


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
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        order.setSellerId(userName);
        return shopOrdersService.findPage(page, rows, order);
    }
}
