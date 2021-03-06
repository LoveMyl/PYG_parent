package cn.itcast.core.common;

/**
 * 常量
 */
public interface Constants {

    public final static String REDIS_CONTENT_LIST = "contentList";

    public final static String REDIS_CATEGORY = "category";
    public final static String REDIS_BRADND_LIST = "brandList";
    public final static String REDIS_SPEC_LIST = "specList";

    public final static String COOKIE_CART_LIST = "pyg_cartList";
    public final static String REDIS_CART_LIST = "pyg_cartList";

    public final static String REDIS_PAYLOG = "payLog";

    public final static String COOKIE_COLLECT_LIST = "pyg_collectList";
    public final static String REDIS_COLLECT_LIST = "pyg_collectList";

    /**
     * 将订单号存入redis,
     */
    public final static String ORDER_TIME_OUT = "orderTimeOut";
}
