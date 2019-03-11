package cn.itcast.core.service;

import cn.itcast.core.dao.order.OrderDao;

import cn.itcast.core.dao.order.OrderItemDao;
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
public class ManagerOrdersServiceimpl implements ManagerOrdersService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;

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
        }
        //分页助手
        PageHelper.startPage(page, rows);

        Page<Order> orderList = (Page<Order>) orderDao.selectByExample(query);

        return new PageResult(orderList.getTotal(), orderList.getResult());
    }


    /**
     * 订单柱形图数据
     * @return
     */

    @Override
    public List<EchrtsResult> findech() {
        //创建一个集合存储数据
        ArrayList<EchrtsResult> echrtsList = new ArrayList<>();
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();
        System.out.println("请求检测");
        //取数据库订单数据
        List<Order> orderList = orderDao.selectByExample(null);
        String time = "";
        String time2 = "";
        double price = 0.0;
        //遍历数据封装进结果对象 放进集合返回
        for (int i = 0, j = 1; i < orderList.size(); i++, j++) {
            Order order = orderList.get(i);
            time = order.getCreateTime().toLocaleString().substring(0, 9);
            if (j < orderList.size()) {
                time2 = orderList.get(j).getCreateTime().toLocaleString().substring(0, 9);
            }
            if (time != null && order.getPayment() != null) {
                if (time.equals(time2)) {
                    price += order.getPayment().doubleValue();
                } else {
                    DecimalFormat d = new DecimalFormat(".##");
                    map.put(time, Double.valueOf(d.format(price)));
                    time = "";
                    time2 = "";
                    price = 0.0;
                }
            }
        }
        if (map != null) {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                EchrtsResult ech = new EchrtsResult();
                ech.setDay(entry.getKey());
                ech.setPrice(entry.getValue().toString());
                echrtsList.add(ech);
            }

        }


        return echrtsList;
    }

    /**
     * 订单折线图数据
     *
     * @return
     */
    @Override
    public List<OrderItem> findechZX() {
        List<OrderItem> ItemList = new ArrayList<OrderItem>();
        List<OrderItem> orderItems = orderItemDao.selectByExample(null);
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            if (map.containsKey(orderItem.getSellerId())) {
                map.put(orderItem.getSellerId(), map.get(orderItem.getSellerId()) + orderItem.getNum());
            } else {
                map.put(orderItem.getSellerId(), orderItem.getNum());
            }
        }
        System.out.println(map);
        if (map != null) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setSellerId(entry.getKey());
                orderItem.setNum(entry.getValue());
                ItemList.add(orderItem);
            }
        }
        return ItemList;
    }


    /**
     * 订单饼状图数据
     * @return
     */
    @Override
    public List<OrderItem> findechB() {

        List<OrderItem> ItemList = new ArrayList<OrderItem>();
        List<OrderItem> orderItems = orderItemDao.selectByExample(null);
        LinkedHashMap<String, BigDecimal> map = new LinkedHashMap<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            if (map.containsKey(orderItem.getSellerId())) {
                BigDecimal add = map.get(orderItem.getTitle()).add(orderItem.getTotalFee());
                map.put(orderItem.getTitle(), add);
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
