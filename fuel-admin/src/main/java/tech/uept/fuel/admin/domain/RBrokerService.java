package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.rocketmq.common.protocol.body.ClusterInfo;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.springframework.stereotype.Component;

import tech.uept.fuel.admin.basic.model.BrokerModel;
import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Component
public class RBrokerService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    public void updateBroker(Integer id, String brokerName, String key, String value) {
        String addr = namesrvService.getAddrById(id);
        client.brokerUpdate(addr, brokerName, key, value);
    }

    public List<BrokerModel> queryList() {
        List<NamesrvModel> list = namesrvService.all();
        List<BrokerModel> returnlist = new ArrayList<>();

        for (NamesrvModel namesrvModel : list) {
            ClusterInfo clusterInfo = client.brokerQueryInfo(namesrvModel.getAddr());
            Collection<BrokerData> brokerDataList = clusterInfo.getBrokerAddrTable().values();
            for (BrokerData brokerData : brokerDataList) {
                HashMap<Long, String> mapNoAddr = brokerData.getBrokerAddrs();
                for (Map.Entry<Long, String> entry : mapNoAddr.entrySet()) {
                    BrokerModel brokerModel = new BrokerModel();
                    long no = entry.getKey();
                    String addr = entry.getValue();
                    brokerModel.setNo((int) no);
                    brokerModel.setAddress(addr);
                    brokerModel.setName(brokerData.getBrokerName());
                    brokerModel.setNamesrvCode(namesrvModel.getCode());
                    brokerModel.setNamesrvId(namesrvModel.getId());
                    brokerModel.setCluster(brokerData.getCluster());
                    returnlist.add(brokerModel);
                }
            }
        }
        return returnlist;
    }

    public Properties getConfig(Integer id, String brokerAddr) {
        String addr = namesrvService.getAddrById(id);
        Properties properties = client.brokerGetConfig(addr, brokerAddr);
        return properties;
    }
}
