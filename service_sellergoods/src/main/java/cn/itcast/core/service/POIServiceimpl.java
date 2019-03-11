package cn.itcast.core.service;


import cn.itcast.core.dao.good.BrandDao;
import cn.itcast.core.dao.good.GoodsDescDao;
import cn.itcast.core.dao.order.OrderItemDao;
import cn.itcast.core.dao.specification.SpecificationDao;
import cn.itcast.core.dao.template.TypeTemplateDao;
import cn.itcast.core.dao.user.UserDao;
import cn.itcast.core.pojo.good.Brand;
import cn.itcast.core.pojo.good.GoodsDesc;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.specification.Specification;
import cn.itcast.core.pojo.template.TypeTemplate;
import cn.itcast.core.pojo.user.User;
import com.alibaba.dubbo.config.annotation.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class POIServiceimpl implements POIService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private GoodsDescDao goodsDescDao;
    @Autowired
    private BrandDao brandDao;
    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private TypeTemplateDao templateDao;

    @Override
    public List<User> download() {
        return userDao.selectByExample(null);
    }

    @Override
    public List<OrderItem> downloadorder() {
        return orderItemDao.selectByExample(null);
    }

    @Override
    public List<GoodsDesc> downloadgoodid() {
        return goodsDescDao.selectByExample(null);
    }

    @Override
    public void addbrand(List<Brand> list) {
        if (list != null) {
            for (Brand brand : list) {
                brandDao.insertSelective(brand);
            }
        }
    }

    @Override
    public void addspe(List<Specification> specList) {
        if (specList!=null){
            for (Specification specification : specList) {
                specificationDao.insertSelective(specification);
            }
        }
    }

    @Override
    public void addtem(List<TypeTemplate> specList) {
        if (specList!=null){
            for (TypeTemplate typeTemplate : specList) {
                templateDao.insertSelective(typeTemplate);
            }
        }
    }

}
