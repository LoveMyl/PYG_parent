package cn.itcast.core.pojo.entity;

import java.io.Serializable;
import java.util.List;

public class ItemCatEntity implements Serializable {
    private Long id;
    private  Long parentId;
    private String name;
    private Long typeId;
    private List<ItemCatEntity> itemCatEntityList;

    @Override
    public String toString() {
        return "ItemCatEntity{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", itemCatEntityList=" + itemCatEntityList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<ItemCatEntity> getItemCatEntityList() {
        return itemCatEntityList;
    }

    public void setItemCatEntityList(List<ItemCatEntity> itemCatEntityList) {
        this.itemCatEntityList = itemCatEntityList;
    }

    public ItemCatEntity(Long id, Long parentId, String name, Long typeId, List<ItemCatEntity> itemCatEntityList) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.typeId = typeId;
        this.itemCatEntityList = itemCatEntityList;
    }

    public ItemCatEntity() {
    }
}
