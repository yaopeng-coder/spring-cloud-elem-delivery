package cn.hust.order.controller;

import cn.hust.order.VO.ResultVO;
import cn.hust.order.converter.OrderForm2OrderDTOConverter;
import cn.hust.order.dto.OrderDTO;
import cn.hust.order.enums.ResultEnum;
import cn.hust.order.exception.SellException;
import cn.hust.order.form.OrderForm;
import cn.hust.order.service.OrderService;
import cn.hust.order.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-17 13:47
 **/
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息（调用商品服务）
     * 3.计算总价
     * 4.扣库存（调用商品服务）
     * 5.订单入库
     * @param orderForm
     * @param bindingResult
     * @return
     */

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        /**
         * 1.参数校验
         */
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数错误,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        /**
         * 2.查询商品信息（调用商品服务）
         * 3.计算总价
         * 4.扣库存（调用商品服务）
         */
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);

    }

    //完结订单
    @GetMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId){
        OrderDTO orderDTO= orderService.finish(orderId);
        return ResultVOUtil.success(orderDTO);

    }
}
