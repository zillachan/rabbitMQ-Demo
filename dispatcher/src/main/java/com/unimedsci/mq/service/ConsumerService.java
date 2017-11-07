package com.unimedsci.mq.service;

import com.unimedsci.mq.domain.Register;

public interface ConsumerService {
    void start() throws Exception;

    void startOnRegister(Register register);
}
