package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;

public interface ManagerSeckillGoodsService {

    /**
     * 后端订单分页显示
     * @param page  当前页码
     * @param rows  显示数量
     * @param seckillOrder
     * @return 返回分页订单数据
     */
    public PageResult findPage(Integer page, Integer rows, SeckillOrder seckillOrder);

}
