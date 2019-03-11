package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import cn.itcast.core.service.ManagerSeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seckillorder")
public class ManagerSeckillController {

    @Reference
    private ManagerSeckillGoodsService managerSeckillGoodsService;

    /**
     * 后端订单分页显示
     * @param page  当前页码
     * @param rows  显示数量
     * @param seckillOrder
     * @return 返回分页订单数据
     */
    @RequestMapping("search")
    public PageResult search(Integer page, Integer rows, @RequestBody SeckillOrder seckillOrder) {
        return managerSeckillGoodsService.findPage(page, rows, seckillOrder);
    }
}
