/*
 * Copyright (c) Unimedsci. All rights reserved.
 */

package com.unimedsci.mq.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 健康记录基本数据模型
 * Created by zilla on 9/12/17.
 */
@Entity
public class Record {
    /**
     * id
     */
    @Id
    private long id;
    /**
     * 指标名
     */
    private String factor_name;
    /**
     * 指标值类型
     */
    private String factor_type;
    /**
     * 指标单位
     */
    private String factor_unit;
    /**
     * 记录开始时间
     */
    private String start_time;
    /**
     * 记录结束时间
     */
    private String end_time;
    /**
     * 指标值
     */
    private String factor_value;

    private int status;

    public Record() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFactor_name() {
        return factor_name;
    }

    public void setFactor_name(String factor_name) {
        this.factor_name = factor_name;
    }

    public String getFactor_type() {
        return factor_type;
    }

    public void setFactor_type(String factor_type) {
        this.factor_type = factor_type;
    }

    public String getFactor_unit() {
        return factor_unit;
    }

    public void setFactor_unit(String factor_unit) {
        this.factor_unit = factor_unit;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getFactor_value() {
        return factor_value;
    }

    public void setFactor_value(String factor_value) {
        this.factor_value = factor_value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", factor_name='" + factor_name + '\'' +
                ", factor_type='" + factor_type + '\'' +
                ", factor_unit='" + factor_unit + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", factor_value='" + factor_value + '\'' +
                ", status=" + status +
                '}';
    }
}
