package cn.itcast.core.controller;


import cn.itcast.core.pojo.entity.GoodsEntity;
import cn.itcast.core.pojo.good.GoodsDesc;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.GoodsService;
import cn.itcast.core.service.SeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.ObjectIdMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seckill")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;

    @Reference
    private GoodsService goodsService;

    /**
     * 查询出全部的秒杀商品
     *z
     * @return
     */
    @RequestMapping("/findAll")
    public List<SeckillGoods> findAll() {

        return seckillGoodsService.findAll();

    }

    /**
     * 查询指定秒杀商品
     * @param id 商品编号
     * @return
     */
    @RequestMapping("/findOne")
    public Map findOne(Long id) {
        SeckillGoods seckillGoods = seckillGoodsService.findOne(id);
        GoodsEntity goodsEntity = goodsService.findOne(seckillGoods.getGoodsId());

        System.out.println();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("seckillGoods",seckillGoods);
        map.put("goodsEntity",goodsEntity);

        return map;
    }

}
