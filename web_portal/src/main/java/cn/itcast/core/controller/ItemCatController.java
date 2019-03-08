package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.ItemCatEntity;
import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.ItemCatEntityService;
import cn.itcast.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;
    @Reference
    private ItemCatEntityService itemCatEntityService;

    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long parentId) {
        List<ItemCat> list = itemCatService.findByParentId(parentId);
        System.out.println(list);
        return list;
    }

    @RequestMapping("/findAll")
    public List<ItemCat> findAll() {
        return itemCatService.findAll();
    }
    //在分类表中根据一级分类查询分类集合
    @RequestMapping("/findByCategoryParentId")
    public List<ItemCatEntity> findByCategoryParentId(Long parentId) {
        //返回分类集合
     return   itemCatEntityService.findRediesByCategoryParentId(parentId);

}}
