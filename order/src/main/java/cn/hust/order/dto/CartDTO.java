package cn.hust.order.dto;

import lombok.Data;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-17 21:05
 **/
@Data
public class CartDTO {

    private String productId;

    private Integer  productQuantity;

    /**
     * 注意这里一定要带上无参构造函数，
     * java.lang.reflect是底层java反射包，从中可以查看java获取class和Method的方法，没有方法可以获取到参数名称
     */
    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
