package tech.uept.fuel.admin.domain;

import java.util.List;

import javax.annotation.Resource;

import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.model.MessagePage;
import tech.uept.fuel.admin.basic.model.MessageQuery;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RMessageService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public List<MessageExt> queryByKey(Integer nid, String topic, String key) {
        String namesrv = namesrvService.getAddrById(nid);
        List<MessageExt> list = client.messageQueryByKey(namesrv, topic, key, 0, 0);
        return list;
    }

    public MessagePage queryMessageByPage(MessageQuery query) {
        return null;
    }

    public MessagePage findByTime(int nid, String topic, long startTime, long endTime) {
        return null;
    }
}
