package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            ClusterInfo cinfo = client.queryBrokerInfo(namesrvModel.getAddr());
            Collection<BrokerData> bs = cinfo.getBrokerAddrTable().values();
            for (BrokerData b : bs) {
                HashMap<Long, String> mapAddrs = b.getBrokerAddrs();
                for (Map.Entry<Long, String> m : mapAddrs.entrySet()) {
                    BrokerModel brokerModel = new BrokerModel();
                    long no = m.getKey();
                    String addr = m.getValue();
                    brokerModel.setNo((int) no);
                    brokerModel.setAddress(addr);
                    brokerModel.setName(b.getBrokerName());
                    brokerModel.setNamesrvCode(namesrvModel.getCode());
                    brokerModel.setNamesrvId(namesrvModel.getId());
                    returnlist.add(brokerModel);
                }
            }
        }
        return returnlist;
    }
}
