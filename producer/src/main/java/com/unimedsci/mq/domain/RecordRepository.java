package com.unimedsci.mq.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordRepository extends CrudRepository<Record, Long> {
    /**
     * 获取最新的n条未处理的记录
     *
     * @return
     */
    List<Record> findFirst100ByStatusIs(int status);

    /**
     * 前10条未处理记录
     *
     * @return
     */
    List<Record> findFirst10ByStatusIs(int status);

    /**
     * 获取最新一条未处理记录
     *
     * @return
     */
    Record findFirstByStatusIs(int status);
}
