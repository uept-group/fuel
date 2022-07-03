package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Component
public class RBrokerService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public void updateBroker(Integer id, String brokerName, String key, String value) {
        String addr = namesrvService.getAddrById(id);
        client.brokerUpdate(addr, brokerName, key, value);
    }
}
