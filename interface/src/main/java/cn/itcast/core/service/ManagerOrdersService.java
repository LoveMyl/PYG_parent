package cn.itcast.core.service;


import cn.itcast.core.pojo.entity.EchrtsResult;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.order.OrderItem;

;import java.util.List;

public interface ManagerOrdersService {

    /**
     * 后端订单分页显示
     *     * @param page  当前页码
     * @param rows  显示数量
     * @param order
     * @return 返回分页订单数据
     */
   PageResult findPage(Integer page, Integer rows, Order order);



    /**
     * 查询柱形图显示数据
     * @return
     */
    List<EchrtsResult> findech();

    /**
     * 查询折线图数据
     * @return
     */

    List<OrderItem> findechZX();

    /**
     * 查询饼状图数据
     * @return
     */
    List<OrderItem> findechB();
}
