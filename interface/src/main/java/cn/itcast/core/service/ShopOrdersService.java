package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.EchrtsResult;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;
import cn.itcast.core.pojo.seller.Seller;
import com.alibaba.dubbo.common.status.Status;

import java.util.List;

public interface ShopOrdersService {

    /**
     * 后端订单分页显示
     *
     * @param page  当前页码
     * @param rows  显示数量
     * @param order
     * @return 返回分页订单数据
     */
    public PageResult findPage(Integer page, Integer rows, Order order);

    public void updateStatus(Long[] ids, String status);

    public List<EchrtsResult> findech(String selleId);

    public List<OrderItem> findechz(String selleId);


}
