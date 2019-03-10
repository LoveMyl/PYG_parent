package cn.itcast.core.service;



import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.GoodsDesc;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.pojo.user.User;

import java.util.List;

public interface POIService {
    List<User> download();

    List<OrderItem> downloadorder();

    List<GoodsDesc> downloadgoodid();

    //导入品牌Excel表
    void addbrand(List<Brand> list);

    //导入规格表
    void addspe(List<Specification> specList);

    //导入分类表
    void addtem(List<TypeTemplate> specList);
}
