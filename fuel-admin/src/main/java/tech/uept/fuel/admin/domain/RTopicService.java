package tech.uept.fuel.admin.domain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RTopicService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public List<String> queryAllTopic(int id) {
        String addr = namesrvService.getAddrById(id);
        return client.queryTopic(addr);
    }
}
