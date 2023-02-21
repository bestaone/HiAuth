package cn.hiauth.auth.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jpaIDGenerator")
    @GenericGenerator(name = "jpaIDGenerator", strategy = "cn.hiauth.auth.utils.JpaIDGenerator")
    private Long id;
    private Long updaterId;
    private Long createrId;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String username;
    private String phoneNum;
    private String password;
    private LocalDateTime regtime;

    private Integer status;

}
