package tech.uept.fuel.admin.basic.rmq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.body.TopicList;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.apache.rocketmq.tools.admin.MQAdminExt;
import org.springframework.stereotype.Component;

@Component
public class RocketmqComplexClient {

    private Map<String, MQAdminExt> cache = new ConcurrentHashMap<String, MQAdminExt>();

    public List<String> queryTopic(String namesrv) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            TopicList topicList = admin.fetchAllTopicList();
            Set<String> topics = topicList.getTopicList();
            return new ArrayList<String>(topics);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private MQAdminExt getAdmin(String namesrv) {
        MQAdminExt admin = cache.get(namesrv);
        if (admin != null) {
            return admin;
        }
        synchronized (cache) {
            admin = cache.get(namesrv);
            if (admin != null) {
                return admin;
            }
            DefaultMQAdminExt defaultAdmin = new DefaultMQAdminExt("fuel-admin");
            defaultAdmin.setNamesrvAddr(namesrv);
            try {
                defaultAdmin.start();
                cache.put(namesrv, defaultAdmin);
                return defaultAdmin;
            } catch (MQClientException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
