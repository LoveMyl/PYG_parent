package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;

import java.util.List;

public interface SecKillOrderService {
    public PageResult findPage(Integer page, Integer rows, SeckillOrder seckillOrder);
   public void deleteById(Long[] ids);

}
