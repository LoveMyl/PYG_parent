package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillGoods;

import java.util.List;

public interface SeckillGoodsService {

    List<SeckillGoods> findAll();

    public PageResult findage(Integer page, Integer rows, SeckillGoods seckillGoods);

    public void updateStatus(Long[] ids, String status);
}