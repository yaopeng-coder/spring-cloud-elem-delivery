package cn.hust.product.common;

import lombok.Data;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-18 12:55
 **/

@Data
public class DecreaseStockInput {

    private String productId;

    private Integer  productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
