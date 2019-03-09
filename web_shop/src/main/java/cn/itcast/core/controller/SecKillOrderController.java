package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import cn.itcast.core.service.SecKillOrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("seckillorder")
public class SecKillOrderController {

    @Reference
    private SecKillOrderService secKillOrderService;


    @RequestMapping("/findAllSecKillOrder")
    public PageResult findAllSecKillOrder(Integer page, Integer rows, @RequestBody SeckillOrder seckillOrder){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillOrder.setSellerId(userName);
        System.out.println(userName);
        return secKillOrderService.findPage(page, rows, seckillOrder);
    }

    @RequestMapping("/deleteSecKillOrder")
    public Result   deleteSecKillOrder(Long[] ids) {
        try {
            secKillOrderService.deleteById(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
