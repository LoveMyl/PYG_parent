package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.ItemCatEntity;

import java.util.List;

public interface ItemCatEntityService {
    public  List<ItemCatEntity> findRediesByCategoryParentId(Long parentId);
}
