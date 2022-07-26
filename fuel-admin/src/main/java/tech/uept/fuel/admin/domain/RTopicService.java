package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.admin.TopicStatsTable;
import org.apache.rocketmq.common.protocol.route.TopicRouteData;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.cache.LocalCache;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;
import tech.uept.fuel.admin.basic.util.Constant;
import tech.uept.fuel.admin.basic.util.PageUtils;

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

    public Object list(Integer id, Integer pageNo, Integer pageSize, String name, String type) {
        if (StringUtils.isEmpty(type)) {
            type = Constant.TOPIC_TYPE_BASE;
        }
        List<String> list = this.list(id);
        list = filter(list, name, type);
        return PageUtils.listToPage(list, pageNo, pageSize);
    }

    private List<String> filter(List<String> list, String name, String type) {
        List<String> returnList = new ArrayList<>();
        for (String s : list) {
            if (StringUtils.isNotEmpty(name)) {
                if (!s.contains(name)) {
                    continue;
                }
            }
            if (type.equals(Constant.TOPIC_TYPE_RETRY)) {
                if (s.startsWith("%RETRY%")) {
                    returnList.add(s);
                }
                continue;
            }
            if (type.equals(Constant.TOPIC_TYPE_DLQ)) {
                if (s.startsWith("%DLQ%")) {
                    returnList.add(s);
                }
                continue;
            }
            returnList.add(s);
        }
        return returnList;
    }

}
