package cn.itcast.core.service;

import cn.itcast.core.dao.order.OrderDao;
import cn.itcast.core.pojo.entity.PageResult;

import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ShopOrdersServiceimpl implements ShopOrdersService {

    @Autowired
    private OrderDao orderDao;

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


}
