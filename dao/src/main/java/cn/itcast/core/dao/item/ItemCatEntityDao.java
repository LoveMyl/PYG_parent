package cn.itcast.core.dao.item;

import cn.itcast.core.pojo.entity.ItemCatEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemCatEntityDao {
    List<ItemCatEntity> findItemCatListByParentId(@Param("parentId") Long parentId);
}
