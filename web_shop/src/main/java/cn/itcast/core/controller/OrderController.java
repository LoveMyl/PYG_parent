package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.EchrtsResult;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.service.ShopOrdersService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @RequestMapping("updateStatus")
    public Result updateStatus(Long[] ids, String status) {
        try {
            shopOrdersService.updateStatus(ids, status);
            return new Result(true, "修改数据状态成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改数据状态失败!");
        }
    }

    @RequestMapping("echarts")
    public List<EchrtsResult> findech() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        return shopOrdersService.findech(userName);
    }

    @RequestMapping("echarts2")
    public List<OrderItem> findechz() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        return shopOrdersService.findechz(userName);
    }
}


