package cn.hiauth.resource.domain;

import cn.hiauth.resource.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order{

    private Long id;
    private Long updaterId;
    private Long createrId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String no;
    private String title;
    private Float totalAmount;
    private OrderStatus status;

}
