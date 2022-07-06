package tech.uept.fuel.admin.domain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.model.QueryMessage;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RMessageService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public List<QueryMessage> queryByKey(Integer nid, String topic, String key) {
        String namesrv = namesrvService.getAddrById(nid);
        List<QueryMessage> list = client.messageQueryByKey(namesrv, topic, key);
        return list;
    }
}
