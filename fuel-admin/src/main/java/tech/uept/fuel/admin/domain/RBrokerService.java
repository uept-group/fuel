package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.protocol.body.ClusterInfo;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import tech.uept.fuel.admin.basic.mapper.BrokerConfigMapper;
import tech.uept.fuel.admin.basic.mapper.po.BrokerConfigPo;
import tech.uept.fuel.admin.basic.model.BrokerConfigModel;
import tech.uept.fuel.admin.basic.model.BrokerModel;
import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;

@Component
public class RBrokerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    @Resource
    private BrokerConfigMapper brokerConfigMapper;

    public void updateBroker(Integer id, String brokerName, String key, String value) {
        String addr = namesrvService.getAddrById(id);
        client.brokerUpdate(addr, brokerName, key, value);
    }

    public List<BrokerModel> queryList() {
        List<NamesrvModel> list = namesrvService.all();
        List<BrokerModel> returnlist = new ArrayList<>();

        for (NamesrvModel namesrvModel : list) {
            try {
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
            } catch (Exception e) {
                BrokerModel brokerModel = new BrokerModel();
                brokerModel.setNo(0);
                brokerModel.setAddress(namesrvModel.getAddr());
                brokerModel.setName("namesrv connect error");
                brokerModel.setNamesrvCode("error");
                brokerModel.setNamesrvId(0);
                brokerModel.setCluster("error");
                returnlist.add(brokerModel);
                log.warn("connection namesrv error: addr is {}", namesrvModel.getAddr());
            }
        }
        return returnlist;
    }

    public Map<String, String> queryConfig(Integer nid, String brokerAddr) {
        String addr = namesrvService.getAddrById(nid);
        Map<String, String> config = client.brokerGetConfig(addr, brokerAddr);
        return config;
    }

    public List<BrokerConfigModel> queryNoteConfig(Integer nid, String brokerAddr) {
        Map<String, String> properties = this.queryConfig(nid, brokerAddr);
        List<BrokerConfigPo> listConfig = brokerConfigMapper.selectList(null);
        List<BrokerConfigModel> returnList = new ArrayList<BrokerConfigModel>();

        for (BrokerConfigPo po : listConfig) {
            String key = po.getKey();
            String value = properties.get(key);
            if (StringUtils.isNotEmpty(value)) {
                BrokerConfigModel brokerConfigModel = new BrokerConfigModel();
                brokerConfigModel.setKey(key);
                brokerConfigModel.setValue(value);
                brokerConfigModel.setDefaultValue(po.getDefaultValue());
                brokerConfigModel.setNote(po.getNote());
                brokerConfigModel.setHotUpdate(po.getHotUpdate());
                returnList.add(brokerConfigModel);
                properties.remove(key);
            }
        }

        for (Map.Entry<String, String> entry : properties.entrySet()) {
            BrokerConfigModel brokerConfigModel = new BrokerConfigModel();
            brokerConfigModel.setKey(entry.getKey());
            brokerConfigModel.setValue(entry.getValue());
            returnList.add(brokerConfigModel);
        }
        return returnList;
    }

    public Map<String, String> queryStats(Integer nid, String brokerAddr) {
        String addr = namesrvService.getAddrById(nid);
        Map<String, String> config = client.brokerGetConfig(addr, brokerAddr);
        return config;
    }
}
