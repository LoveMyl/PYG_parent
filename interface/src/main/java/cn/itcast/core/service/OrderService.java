package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.log.PayLog;
import cn.itcast.core.pojo.order.Order;

import java.util.List;

public interface OrderService {

    /**
     * 保存订单
     * 涉及到三张表, payLog支付日志, order订单表, orderItem订单详情表
     *
     * @param order 页面提交的订单对象数据, 这个对象不直接保存到数据库中, 只是需要页面提交过来的
     *              收货人地址, 收货人姓名, 收货人电话, 支付方式等信息.
     */
    public void add(Order order);

    /**
     * 根据支付单号修改, 支付状态为已支付
     *
     * @param out_trade_no 支付单号
     */
    public void updatePayLogAndOrderStatus(String out_trade_no);

    /**
     * 分页查询 订单数据
     *
     * @param page     当前页
     * @param rows     每页显示个数
     * @param userName 当前登陆者用户名
     * @param status   订单状态
     * @return
     */
    public abstract PageResult search(Integer page, Integer rows, String userName, String status);

    /**
     * 查询payLog支付日志,
     *
     * @param orderIdStr
     * @return
     */
    public abstract PayLog findPayLog(String orderIdStr);

    /**
     * 订单超时,修改订单状态
     * @param orderIdStr 订单号
     */
    public abstract void updateOrderStatus(String orderIdStr);

}
