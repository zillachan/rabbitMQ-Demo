package com.unimedsci.mq.service;

import com.unimedsci.mq.domain.Record;

import java.util.List;

public interface RecordService {
    /**
     * 获取100条记录
     *
     * @return
     */
    List<Record> get100Record();

    /**
     * 获取10条记录
     *
     * @return
     */
    List<Record> get10Record();

    /**
     * 获取单条记录
     *
     * @return
     */
    Record getOneRecord();

    void start(String host, String exchangeName, String routingKey);
}
