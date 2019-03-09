package cn.itcast.core.controller;


import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.SeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SeckillGoods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;

    /**
     * 查询出全部的秒杀商品
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<SeckillGoods> findAll() {

        return seckillGoodsService.findAll();

    }
}
