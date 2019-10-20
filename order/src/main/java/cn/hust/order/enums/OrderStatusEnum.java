package cn.hust.order.enums;

import lombok.Getter;

/**
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-07 19:18
 **/
@Getter
public enum  OrderStatusEnum implements CodeEnum{
    NEW(0,"新下单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;

    private Integer code;
    private String message;

    OrderStatusEnum(Integer code, String message ) {
        this.code = code;
        this.message = message;
    }


}
