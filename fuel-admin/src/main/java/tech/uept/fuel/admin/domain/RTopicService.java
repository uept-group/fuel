package tech.uept.fuel.admin.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.rocketmq.common.admin.TopicStatsTable;
import org.apache.rocketmq.common.protocol.route.TopicRouteData;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.cache.LocalCache;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Service
public class RTopicService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    @Resource
    private LocalCache localCache;

    public List<String> list(int id) {
        String addr = namesrvService.getAddrById(id);
        return client.topicList(addr);
    }

    public TopicStatsTable status(Integer id, String topic) {
        String addr = namesrvService.getAddrById(id);
        TopicStatsTable topicStatsTable = client.topicStatus(addr, topic);
        return topicStatsTable;
    }

    public Object route(Integer id, String topic) {
        String addr = namesrvService.getAddrById(id);
        TopicRouteData topicRouteData = client.topicRoute(addr, topic);
        return topicRouteData;
    }

    public List<String> consumer(Integer id, String topic) {
        String addr = namesrvService.getAddrById(id);
        List<String> consumerList = client.topicQueryConsumer(addr, topic);
        return consumerList;
    }

    public Object list(Integer id, Integer pageNo, Integer pageSize) {
        List<String> list = this.list(0);
        int first = (pageNo - 1) * pageSize;
        int last = pageNo * pageSize;
        if (list.size() < first) {
            return null;
        }
        if (list.size() < last) {
            last = list.size();
        }
        List<String> list2 = list.subList(first, last);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list.size());
        map.put("list", list2);
        return null;
    }

}
