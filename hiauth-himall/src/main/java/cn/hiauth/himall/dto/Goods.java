package cn.hiauth.himall.dto;


import lombok.Data;

import java.util.Date;

@Data
public class Goods{

    private Long id;
    private Long updaterId;
    private Long createrId;
    private Date createTime;
    private Date updateTime;
    private String title;
    private Float price;
    private Integer amount;

}
