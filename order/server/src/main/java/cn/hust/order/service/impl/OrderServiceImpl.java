package cn.hust.order.service.impl;


import cn.hust.order.dataobject.OrderDetail;
import cn.hust.order.dataobject.OrderMaster;
import cn.hust.order.dto.OrderDTO;
import cn.hust.order.enums.OrderStatusEnum;
import cn.hust.order.enums.PayStatusEnum;
import cn.hust.order.repository.OrderDetailRepository;
import cn.hust.order.repository.OrderMasterRepository;
import cn.hust.order.service.OrderService;
import cn.hust.order.utils.KeyUtil;
import cn.hust.product.client.ProductClient;
import cn.hust.product.common.DecreaseStockInput;
import cn.hust.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-08 10:56
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
     private ProductClient productClient;



    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.getUniqueKey();

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                                     .map(e -> e.getProductId())
                                     .collect(Collectors.toList());

        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        for(OrderDetail orderDetail: orderDTO.getOrderDetailList()){
            for(ProductInfoOutput productInfo: productInfoList){
                if(productInfo.getProductId().equals(orderDetail.getProductId())){
                    amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());
                    orderDetail.setOrderId(orderId);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        // 减库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(amount);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());


        orderMasterRepository.save(orderMaster);

        return orderDTO;


    }
}
