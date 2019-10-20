package cn.hust.order.exception;


import cn.hust.order.enums.ResultEnum;
import lombok.Getter;

/**
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-08 10:07
 **/
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
