package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.apache.rocketmq.common.protocol.body.ProducerConnection;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RProducerService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public ProducerConnection producerConnection(Integer id, String producerGroup, String topic) {
        String namesrv = namesrvService.getAddrById(id);
        ProducerConnection producerConnection = client.producerConnection(namesrv, producerGroup, topic);
        return producerConnection;
    }
}
