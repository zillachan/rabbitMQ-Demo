package com.unimedsci.mq.domain;

import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Register implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    /**
     * 接口地址
     */
    private String address;

    /**
     * exchange名称
     */
    private String exchangeName;
    /**
     * 关注的Topic
     */
    private String routingKey;

    private String queueName;
    /**
     * 创建时间
     */
    @CreatedDate
    private DateTime createTime;
    /**
     * 最后更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date updateTime;

    /**
     * 注册状态，0不可用
     */
    private int status;

    public Register() {

    }

    public Register(String address, String exchangeName, String routingKey, String queueName) {
        this.address = address;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.queueName = queueName;
//        this.createTime = DateTime.now();
        this.status = 1;
    }

}
