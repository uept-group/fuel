package tech.uept.fuel.admin.api;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.app.BrokerConfigTask;
import tech.uept.fuel.admin.basic.model.BrokerConfigModel;
import tech.uept.fuel.admin.domain.RBrokerService;

@RestController
@RequestMapping("/rbroker")
public class RBrokerAction {

    @Resource
    private RBrokerService rBrokerService;

    @Resource
    private BrokerConfigTask brokerConfigTask;

    @RequestMapping("/update")
    public void update(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String brokerName = (String) map.get("brokerName");
        String key = (String) map.get("key");
        String value = (String) map.get("value");
        rBrokerService.updateBroker(id, brokerName, key, value);
    }

    @RequestMapping("/getConfig")
    public Object getConfig(@RequestParam(name = "nid") Integer nid, @RequestParam(name = "addr") String addr) {
        List<BrokerConfigModel> properties = rBrokerService.queryNoteConfig(nid, addr);
        return properties;
    }

    @RequestMapping("/queryList")
    public Object queryList() {
        return rBrokerService.queryList();
    }

    @RequestMapping("/initConfig")
    public Object initConfig() {
        brokerConfigTask.doCheck();
        return "";
    }

}
