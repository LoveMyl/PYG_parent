package cn.itcast.core.service;

import cn.itcast.core.dao.seckill.SeckillOrderDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import cn.itcast.core.pojo.seckill.SeckillOrderQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ManagerSeckillGoodsServiceImpl implements ManagerSeckillGoodsService {

    @Autowired
    private SeckillOrderDao seckillOrderDao;
    /**
     * 管理后台显示秒杀订单
     * @param page  当前页码
     * @param rows  显示数量
     * @param seckillOrder
     * @return
     */

    @Override
    public PageResult findPage(Integer page, Integer rows, SeckillOrder seckillOrder) {
        //条件对象
        SeckillOrderQuery query = new SeckillOrderQuery();
        if (seckillOrder != null) {
            //条件对象
            SeckillOrderQuery.Criteria criteria = query.createCriteria();
            if (seckillOrder.getId() != null && !"".equals(seckillOrder.getId())) {
                //条件
                criteria.andIdEqualTo(seckillOrder.getId());
            }
        }
        //分页助手
        PageHelper.startPage(page, rows);

        Page<SeckillOrder> SeckillOrderList = (Page<SeckillOrder>) seckillOrderDao.selectByExample(query);

        return new PageResult(SeckillOrderList.getTotal(), SeckillOrderList.getResult());
    }
}
