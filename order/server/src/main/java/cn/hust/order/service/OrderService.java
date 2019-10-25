package cn.hust.order.service;


import cn.hust.order.dto.OrderDTO;

/**
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-08 09:47
 **/
public interface OrderService {

    /** 创建订单. */
    OrderDTO create(OrderDTO orderDTO);


    /**
     * 完结订单
     */

    OrderDTO finish(String orderId);


}
