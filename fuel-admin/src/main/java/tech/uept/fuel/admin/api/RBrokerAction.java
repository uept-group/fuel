package tech.uept.fuel.admin.api;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import tech.uept.fuel.admin.app.BrokerConfigTask;
import tech.uept.fuel.admin.basic.model.BrokerConfigModel;
import tech.uept.fuel.admin.bean.BrokerState;
import tech.uept.fuel.admin.domain.RBrokerService;

@RestController
@RequestMapping("/rbroker")
public class RBrokerAction {

    @Resource
    private RBrokerService rBrokerService;

    @Resource
    private BrokerConfigTask brokerConfigTask;

    @PostMapping("/update")
    public void update(@RequestBody HashMap<String, Object> map) {
        Integer id = (Integer) map.get("id");
        String brokerName = (String) map.get("brokerName");
        String key = (String) map.get("key");
        String value = (String) map.get("value");
        rBrokerService.updateBroker(id, brokerName, key, value);
    }

    @GetMapping("/findConfig")
    public Object findConfig(@RequestParam(name = "nid") Integer nid, @RequestParam(name = "addr") String addr) {
        List<BrokerConfigModel> properties = rBrokerService.queryNoteConfig(nid, addr);
        return properties;
    }

    @GetMapping("/findStats")
    public List<BrokerState> findStats(@RequestParam(name = "nid") Integer nid,
            @RequestParam(name = "addr") String addr) {
        return rBrokerService.queryStats(nid, addr);
    }

    @GetMapping("/queryList")
    public Object queryList() {
        return rBrokerService.queryList();
    }

    @GetMapping("/findNameList")
    public Object findBrokerNameList(@RequestParam(name = "nid") Integer nid) {
        return rBrokerService.findNameList(nid);
    }

    @GetMapping("/initConfig")
    public Object initConfig() {
        brokerConfigTask.doCheck();
        return "";
    }

}
