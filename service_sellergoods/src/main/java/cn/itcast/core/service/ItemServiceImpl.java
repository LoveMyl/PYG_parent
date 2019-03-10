package cn.itcast.core.service;

import cn.itcast.core.dao.item.ItemDao;
import cn.itcast.core.pojo.item.Item;
import cn.itcast.core.pojo.item.ItemQuery;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<Item> findByGoodsId(Long goodsId) {
        ItemQuery itemQuery = new ItemQuery();
        ItemQuery.Criteria criteria = itemQuery.createCriteria();
        if (goodsId!=null){
            criteria.andGoodsIdEqualTo(goodsId);
        }
        List<Item> itemList = itemDao.selectByExample(itemQuery);
        return itemList;
    }

    @Override
    public Item findOne(Long id) {
        Item item = itemDao.selectByPrimaryKey(id);
        return item;
    }


}
