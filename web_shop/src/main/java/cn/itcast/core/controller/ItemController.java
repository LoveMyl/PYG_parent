package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.ItemCatService;
import cn.itcast.core.service.ItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.opensaml.xml.encryption.Public;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Reference
    private ItemService itemService;

    @RequestMapping("/findByGoodsId")
    public List<Item> findByGoodsId(Long goodsId) {
        List<Item> list = itemService.findByGoodsId(goodsId);
        return list;
    }

    @RequestMapping("/findOne")
    public Item findOne(Long id) {
        return itemService.findOne(id);
    }



}
