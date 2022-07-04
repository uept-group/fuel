package tech.uept.fuel.admin.domain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RConsumerService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public Object list(Integer id) {
        String nAdddr = namesrvService.getAddrById(id);
        List<String> list = client.consumerList(nAdddr);
        return list;
    }
}
