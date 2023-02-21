package cn.hiauth.himall.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Order{

    private Long id;
    private Long updaterId;
    private Long createrId;
    private Date createTime;
    private Date updateTime;
    private String no;
    private String title;
    private Float totalAmount;

}
