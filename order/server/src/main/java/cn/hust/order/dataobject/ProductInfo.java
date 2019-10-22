package cn.hust.order.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 * @program: elem-delivery
 * @author: yaopeng
 * @create: 2019-10-07 14:42
 **/
@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable{


    private static final long serialVersionUID = -328479079298582777L;

    @Id
    private String productId;

    /** 商品名称. */
    private String productName;

    /**  单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

   /** 商品状态,0正常1下架. */
    private Integer productStatus ;

    /** 类目编号. */
    private Integer categoryType;

    /** 产品上架时间. */
    private Date createTime;

    /** 产品更新时间. */
    private Date updateTime;


}
