package cn.itcast.core.service;

import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.seckill.SeckillGoods;

import java.util.List;

public interface ItemService {
    public List<Item> findByGoodsId(Long goodsId);
    public Item findOne(Long id);
}
