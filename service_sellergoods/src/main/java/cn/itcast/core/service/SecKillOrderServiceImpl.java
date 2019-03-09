package cn.itcast.core.service;

import cn.itcast.core.dao.seckill.SeckillOrderDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import cn.itcast.core.pojo.seckill.SeckillOrderQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Queue;

@Service
public class SecKillOrderServiceImpl implements SecKillOrderService {

    @Autowired
    private SeckillOrderDao seckillOrderDao;

    @Override
    public PageResult findPage(Integer page, Integer rows, SeckillOrder seckillOrder) {
        SeckillOrderQuery seckillOrderQuery = new SeckillOrderQuery();
        if (seckillOrder != null) {
            SeckillOrderQuery.Criteria criteria = seckillOrderQuery.createCriteria();
            if (seckillOrder.getId() != null && !"".equals(seckillOrder.getId())) {
                criteria.andIdEqualTo(seckillOrder.getId());
            }
            if (seckillOrder.getSellerId() != null && !"".equals(seckillOrder.getSellerId())) {
                criteria.andSellerIdEqualTo(seckillOrder.getSellerId());
            }
        }
        PageHelper.startPage(page, rows);
        Page<SeckillOrder> seckillOrders = (Page<SeckillOrder>) seckillOrderDao.selectByExample(seckillOrderQuery);
    /*    System.out.println(seckillOrders.getResult().get(0).getId());*/
        return new PageResult(seckillOrders.getTotal(), seckillOrders.getResult());
    }

    @Override
    public void deleteById(Long[] ids) {
        if (ids!=null){
            for (Long id : ids) {
             seckillOrderDao.deleteByPrimaryKey(id);
            }
        }
    }
}
