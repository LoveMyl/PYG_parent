package cn.itcast.core.service;

import cn.itcast.core.dao.order.OrderItemDao;
import cn.itcast.core.pojo.entity.Sale;
import cn.itcast.core.pojo.order.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SaleServiceimpl implements SaleService {
    @Autowired
    private OrderItemDao orderItemDao;
    @Override
    public List<Sale> getsale() {
        return null;
    }
}
