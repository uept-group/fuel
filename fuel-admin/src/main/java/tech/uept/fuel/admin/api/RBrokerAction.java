package tech.uept.fuel.admin.api;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.RBrokerService;

@RestController
@RequestMapping("/rbroker")
public class RBrokerAction {

    @Resource
    private RBrokerService rBrokerService;

    @RequestMapping("/update")
    public void update(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String brokerName = (String) map.get("brokerName");
        String key = (String) map.get("key");
        String value = (String) map.get("value");
        rBrokerService.updateBroker(id, brokerName, key, value);
    }

    @RequestMapping("/queryList")
    public Object queryList() {
        return rBrokerService.queryList();
    }

}
