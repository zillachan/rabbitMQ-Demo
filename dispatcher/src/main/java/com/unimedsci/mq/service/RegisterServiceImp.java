package com.unimedsci.mq.service;

import com.unimedsci.mq.common.ResponseModel;
import com.unimedsci.mq.domain.Register;
import com.unimedsci.mq.domain.RegisterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImp implements RegisterService {
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImp.class);

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private ConsumerService consumerService;

    @Override
    public ResponseModel<String> register(String apiAddress, String exchangeName, String routingKey, String queueName) {
        String actuallyRoutingKey = "com.unimedsci.mq." + routingKey;
        Register register = registerRepository.findFirstByAddressAndExchangeNameAndRoutingKey(apiAddress, exchangeName, actuallyRoutingKey);
        if (register != null) {
            if (register.getStatus() == 0) {
                register.setStatus(1);
                registerRepository.save(register);
                consumerService.startOnRegister(register);
            }
        } else {
            register = new Register(apiAddress, exchangeName, actuallyRoutingKey,queueName);
            registerRepository.save(register);
            consumerService.startOnRegister(register);
        }
        log.info("注册成功：apiAddress：" + apiAddress + " exchangeName：" + exchangeName + " routingKey：" + actuallyRoutingKey);
        return new ResponseModel<>("注册成功");
    }

    @Override
    public ResponseModel<String> unregister(String apiAddress, String exchangeName, String routingKey) {
        String actuallyRoutingKey = "com.unimedsci.mq." + routingKey;
        Register register = registerRepository.findFirstByAddressAndExchangeNameAndRoutingKey(apiAddress, exchangeName, actuallyRoutingKey);
        if (register != null) {
            if (register.getStatus() == 1) {
                register.setStatus(0);
                registerRepository.save(register);
            }
        }
        return new ResponseModel<>("解除注册成功");
    }
}
