package tech.uept.fuel.admin.domain;

import java.util.List;

import javax.annotation.Resource;

import org.apache.rocketmq.common.admin.TopicStatsTable;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RTopicService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public List<String> list(int id) {
        String addr = namesrvService.getAddrById(id);
        return client.topicList(addr);
    }

    public TopicStatsTable status(Integer id, String topic) {
        String addr = namesrvService.getAddrById(id);
        TopicStatsTable topicStatsTable = client.topicStatus(addr, topic);
        return topicStatsTable;
    }
}
