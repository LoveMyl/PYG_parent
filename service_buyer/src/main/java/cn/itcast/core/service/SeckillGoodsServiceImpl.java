package cn.itcast.core.service;

import cn.itcast.core.dao.seckill.SeckillGoodsDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.pojo.seckill.SeckillGoodsQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    @Override
    public PageResult findage(Integer page, Integer rows, SeckillGoods seckillGoods) {
        SeckillGoodsQuery seckillGoodsQuery = new SeckillGoodsQuery();
        System.out.println(seckillGoods.getStatus());
        if(seckillGoods!=null){
            SeckillGoodsQuery.Criteria criteria = seckillGoodsQuery.createCriteria();
            if (seckillGoods.getId()!=null&&!"".equals(seckillGoods.getId())){
                criteria.andIdEqualTo(seckillGoods.getId());
            }
            if(seckillGoods.getSellerId()!=null&&!"".equals(seckillGoods.getSellerId())){
                criteria.andSellerIdEqualTo(seckillGoods.getSellerId());
            }
            if(seckillGoods.getStatus()!=null&&!"".equals(seckillGoods.getStatus())){
                criteria.andStatusEqualTo(seckillGoods.getStatus());
            }
        }
        PageHelper.startPage(page,rows);
        Page<SeckillGoods> seckillGoods1 = (Page<SeckillGoods>) seckillGoodsDao.selectByExample(seckillGoodsQuery);
        return new PageResult(seckillGoods1.getTotal(), seckillGoods1.getResult());
    }


    @Override
    public void updateStatus(Long[] ids, String status) {
        if (ids!=null){
            for (Long id : ids) {
                SeckillGoods seckillGoods = new SeckillGoods();
                seckillGoods.setId(id);
                seckillGoods.setStatus(status);
                seckillGoodsDao.updateByPrimaryKeySelective(seckillGoods);
            }
        }
    }
}
