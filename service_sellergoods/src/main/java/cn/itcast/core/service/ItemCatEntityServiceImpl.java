package cn.itcast.core.service;

import cn.itcast.core.dao.item.ItemCatEntityDao;
import cn.itcast.core.pojo.entity.ItemCatEntity;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class ItemCatEntityServiceImpl implements ItemCatEntityService {
    @Autowired
    private ItemCatEntityDao itemCatEntityDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    //从redies数据库中查询分类集合
    public List<ItemCatEntity> findRediesByCategoryParentId(Long parentId) {
        //redies中获取
        List<ItemCatEntity> itemCatEntityList = (List<ItemCatEntity>) redisTemplate.boundHashOps("itemcat").get("indexItemCat");
        if (itemCatEntityList == null) {
            //本地库中根据parent——id查询集合
            List<ItemCatEntity> itemCatListByParentId = itemCatEntityDao.findItemCatListByParentId(parentId);
            for (ItemCatEntity itemCatEntity : itemCatListByParentId) {
                //遍历一级分类，根据一级分类parentid查询二级分类
                List<ItemCatEntity> itemCatListByParentId1 = itemCatEntityDao.findItemCatListByParentId(itemCatEntity.getId());
                for (ItemCatEntity catEntity : itemCatListByParentId1) {
                    //遍历二级分类，根据二级分类查询三级分类集合
                    List<ItemCatEntity> itemCatListByParentId2 = itemCatEntityDao.findItemCatListByParentId(catEntity.getId());
                    //封装三级分类集合到二级分类中
                    catEntity.setItemCatEntityList(itemCatListByParentId2);
                }
                //封装二级分类到一级集合
                itemCatEntity.setItemCatEntityList(itemCatListByParentId1);
            }
            //存入redies数据库中
            redisTemplate.boundHashOps("itemcat").put("indexItemCat", itemCatListByParentId);
            return itemCatListByParentId;

        }
        return itemCatEntityList;

    }
}
