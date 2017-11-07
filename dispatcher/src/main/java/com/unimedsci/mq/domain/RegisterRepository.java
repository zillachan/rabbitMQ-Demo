package com.unimedsci.mq.domain;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegisterRepository extends CrudRepository<Register, Long> {

    /**
     * 根据注册地址和topic判断是否已经注册过了
     *
     * @param address
     * @param exchangeName
     * @param routingKey
     * @return
     */
    Register findFirstByAddressAndExchangeNameAndRoutingKey(String address, String exchangeName, String routingKey);

    /**
     * 通过服务器地址，获取所有记录
     *
     * @param address
     * @return
     */
    List<Register> findAllByAddress(String address);

    /**
     * 获取所有可用记录
     *
     * @return
     */
    @Query("select r from Register r where r.status = 0")
    List<Register> findAllByStatusNot0();

    /**
     * 返回该地址下可用的记录列表
     *
     * @param address
     * @return
     */
    @Query("select r from Register r where r.status = 0 and r.address=?1 ")
    List<Register> findAllByAddressAndStatusNot0(String address);
}
