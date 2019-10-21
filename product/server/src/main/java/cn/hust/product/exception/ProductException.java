package cn.hust.product.exception;

import cn.hust.product.enums.ResultEnum;

/**
 * @program: spring-cloud-elem-delivery
 * @author: yaopeng
 * @create: 2019-10-17 20:59
 **/
public class ProductException  extends RuntimeException{

    private Integer code;

    public ProductException(Integer code, String message){
        super(message);
        this.code = code;
    }

   public  ProductException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }


}
