package tech.uept.fuel.admin.basic.rmq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.admin.TopicStatsTable;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.body.ClusterInfo;
import org.apache.rocketmq.common.protocol.body.GroupList;
import org.apache.rocketmq.common.protocol.body.KVTable;
import org.apache.rocketmq.common.protocol.body.ProducerConnection;
import org.apache.rocketmq.common.protocol.body.SubscriptionGroupWrapper;
import org.apache.rocketmq.common.protocol.body.TopicList;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.apache.rocketmq.common.protocol.route.TopicRouteData;
import org.apache.rocketmq.common.subscription.SubscriptionGroupConfig;
import org.apache.rocketmq.tools.admin.DefaultMQAdminExt;
import org.apache.rocketmq.tools.admin.MQAdminExt;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import tech.uept.fuel.admin.basic.model.QueryMessage;
import tech.uept.fuel.admin.basic.util.BeanUtils;

@Component
public class RocketmqComplexClient {

    private Map<String, MQAdminExt> cache = new ConcurrentHashMap<String, MQAdminExt>();

    public List<String> topicList(String namesrv) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            TopicList topicList = admin.fetchAllTopicList();
            Set<String> topics = topicList.getTopicList();
            return new ArrayList<String>(topics);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TopicStatsTable topicStatus(String namesrv, String topic) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            TopicStatsTable s = admin.examineTopicStats(topic);
            return s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TopicRouteData topicRoute(String namesrv, String topic) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            TopicRouteData d = admin.examineTopicRouteInfo(topic);
            return d;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> topicQueryConsumer(String namesrv, String topic) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            GroupList d = admin.queryTopicConsumeByWho(topic);
            return new ArrayList<String>(d.getGroupList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ClusterInfo brokerQueryInfo(String namesrv) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            ClusterInfo info = admin.examineBrokerClusterInfo();
            return info;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void brokerUpdate(String namesrv, String brokerName, String key, String value) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            ClusterInfo info = admin.examineBrokerClusterInfo();
            BrokerData brokerData = info.getBrokerAddrTable().get(brokerName);
            for (String addr : brokerData.getBrokerAddrs().values()) {
                Properties ps = new Properties();
                ps.setProperty(key, value);
                admin.updateBrokerConfig(addr, ps);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> brokerGetConfig(String namesrv, String brokerAddr) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            Map<String, String> map = new HashMap<String, String>();
            Properties properties = admin.getBrokerConfig(brokerAddr);
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                map.put((String) entry.getKey(), (String) entry.getValue());
            }
            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> brokerGetStats(String namesrv, String brokerAddr) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            KVTable kvTable = admin.fetchBrokerRuntimeStats(brokerAddr);
            return kvTable.getTable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> consumerList(String namesrv) {
        MQAdminExt admin = getAdmin(namesrv);
        Set<String> set = new HashSet<String>();
        try {
            ClusterInfo clusterInfo = admin.examineBrokerClusterInfo();
            for (BrokerData brokerData : clusterInfo.getBrokerAddrTable().values()) {
                SubscriptionGroupWrapper subscriptionGroupWrapper = admin
                        .getAllSubscriptionGroup(brokerData.selectBrokerAddr(), 3000L);
                set.addAll(subscriptionGroupWrapper.getSubscriptionGroupTable().keySet());
            }
            return new ArrayList<>(set);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SubscriptionGroupConfig> consumerConfig(String namesrv, String consumer) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            ClusterInfo clusterInfo = admin.examineBrokerClusterInfo();
            List<SubscriptionGroupConfig> list = new ArrayList<>();
            for (String brokerName : clusterInfo.getBrokerAddrTable().keySet()) {
                String brokerAddress = clusterInfo.getBrokerAddrTable().get(brokerName).selectBrokerAddr();
                SubscriptionGroupConfig subscriptionGroupConfig = admin.examineSubscriptionGroupConfig(brokerAddress,
                        consumer);
                list.add(subscriptionGroupConfig);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProducerConnection producerConnection(String namesrv, String producerGroup, String topic) {
        MQAdminExt admin = getAdmin(namesrv);
        try {
            ProducerConnection producerConnection = admin.examineProducerConnectionInfo(producerGroup, topic);
            return producerConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<MessageExt> messageQueryByKey(String namesrv, String topic, String key, long startTime, long endTime) {
        if (endTime == 0) {
            endTime = System.currentTimeMillis();
        }
        key = null;
        MQAdminExt admin = getAdmin(namesrv);
        try {
            List<MessageExt> list = new ArrayList<MessageExt>();
            QueryResult queryResult = admin.queryMessage(topic, key, 64, startTime, endTime);
            if (queryResult == null || CollectionUtils.isEmpty(queryResult.getMessageList())) {
                return list;
            }
            for (MessageExt messageExt : queryResult.getMessageList()) {
//                QueryMessage queryMessage = BeanUtils.msgToMsg(messageExt);
                list.add(messageExt);
            }
            return list;
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
            String name = "fuel-admin:" + namesrv;

            DefaultMQAdminExt defaultAdmin = new DefaultMQAdminExt(name);
            defaultAdmin.setInstanceName(name);

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
