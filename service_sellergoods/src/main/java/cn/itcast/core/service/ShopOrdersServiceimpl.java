package cn.itcast.core.service;

import cn.itcast.core.dao.order.OrderDao;
import cn.itcast.core.dao.order.OrderItemDao;
import cn.itcast.core.dao.seller.SellerDao;
import cn.itcast.core.pojo.entity.EchrtsResult;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.order.OrderQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Service

public class ShopOrdersServiceimpl implements ShopOrdersService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private SellerDao sellerDao;

    /**
     * 后台管理订单查询分页显示
     *
     * @param page  当前页码
     * @param rows  显示数量
     * @param order
     * @return 返回封装订单结果集
     */
    @Override
    public PageResult findPage(Integer page, Integer rows, Order order) {
        //条件对象
        OrderQuery query = new OrderQuery();
        if (order != null) {
            //条件对象
            OrderQuery.Criteria criteria = query.createCriteria();
            if (order.getOrderId() != null && !"".equals(order.getOrderId())) {
                //条件
                criteria.andOrderIdEqualTo(order.getOrderId());
            }
            if (order.getSellerId() != null && !"".equals(order.getSellerId())) {
                criteria.andSellerIdEqualTo(order.getSellerId());
            }
        }

        //分页助手
        PageHelper.startPage(page, rows);

        Page<Order> orderList = (Page<Order>) orderDao.selectByExample(query);

        return new PageResult(orderList.getTotal(), orderList.getResult());
    }

    @Override
    public void updateStatus(Long[] ids, String status) {
        Order order = new Order();
        order.setStatus(status);
        if (ids != null) {
            for (Long id : ids) {
                order.setOrderId(id);
                orderDao.updateByPrimaryKeySelective(order);
            }
        }
    }

    @Override
    public List<EchrtsResult> findech(String selleId) {
        //创建一个集合存储数据
        ArrayList<EchrtsResult> echrtsList = new ArrayList<>();

        System.out.println("请求检测");
        //取数据库订单数据
        OrderQuery query = new OrderQuery();
        OrderQuery.Criteria criteria = query.createCriteria();
        criteria.andSellerIdEqualTo(selleId);
        List<Order> orderList = orderDao.selectByExample(query);
        LinkedHashMap<String, BigDecimal> map = new LinkedHashMap<>();
        String time = "";
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            time = order.getCreateTime().toLocaleString().substring(0, 9);
            if (map.containsKey(time)) {
                map.put(time, map.get(time).add(order.getPayment()));
            } else {
                map.put(time, order.getPayment());
            }
        }
        if (map != null) {
            for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                EchrtsResult echrtsResult = new EchrtsResult();
                echrtsResult.setDay(entry.getKey());
                echrtsResult.setPrice(entry.getValue().toString());
                echrtsList.add(echrtsResult);
            }
        }

        return echrtsList;
    }

    /**
     * 订单统计折线图
     *
     * @return
     */
    @Override
    public List<OrderItem> findechz(String selleId) {
        List<OrderItem> ItemList = new ArrayList<OrderItem>();
        OrderQuery query1 = new OrderQuery();
        OrderQuery.Criteria criteria = query1.createCriteria();
        criteria.andSellerIdEqualTo(selleId);
        List<OrderItem> orderItems = orderItemDao.selectByExample(query1);
        LinkedHashMap<String, BigDecimal> map = new LinkedHashMap<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            if (map.containsKey(orderItem.getTitle())) {
                map.put(orderItem.getTitle(), map.get(orderItem.getTitle()).add(orderItem.getTotalFee()));
            } else {
                map.put(orderItem.getTitle(), orderItem.getTotalFee());
            }
        }
        System.out.println(map);
        if (map != null) {
            for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setTitle(entry.getKey());
                orderItem.setTotalFee(entry.getValue());
                ItemList.add(orderItem);
            }
        }
        return ItemList;
    }

}
