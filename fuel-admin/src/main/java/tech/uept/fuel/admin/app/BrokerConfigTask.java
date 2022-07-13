package tech.uept.fuel.admin.app;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import tech.uept.fuel.admin.basic.mapper.BrokerConfigMapper;
import tech.uept.fuel.admin.basic.mapper.po.BrokerConfigPo;
import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.domain.NamesrvService;
import tech.uept.fuel.admin.domain.RBrokerService;

@Component
public class BrokerConfigTask {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RBrokerService brokerService;

    @Resource
    private BrokerConfigMapper brokerConfigMapper;

    // 每天晚上1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void doCheck() {
        List<NamesrvModel> list = namesrvService.all();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        NamesrvModel n = list.get(0);
        Map<String, String> properties = brokerService.queryConfig(n.getId(),
                brokerService.queryList().get(0).getAddress());
        List<BrokerConfigPo> listBrokerConfigPos = brokerConfigMapper.selectList(null);
        for (BrokerConfigPo po : listBrokerConfigPos) {
            Object obj = properties.get(po.getKey());
            if (obj != null) {
                properties.remove(po.getKey());
            }
        }
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            BrokerConfigPo po = new BrokerConfigPo();
            po.setKey(entry.getKey());
            po.setValue(entry.getValue());
            brokerConfigMapper.insert(po);
        }
    }
}
