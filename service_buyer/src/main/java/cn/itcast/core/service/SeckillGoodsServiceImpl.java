package cn.itcast.core.service;

import cn.itcast.core.dao.seckill.SeckillGoodsDao;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;

    /**
     * 查询出全部的秒杀商品
     *
     * @return
     */
    @Override
    public List<SeckillGoods> findAll() {
        List<SeckillGoods> seckillGoods = seckillGoodsDao.selectByExample(null);
        return seckillGoods;
    }
}
