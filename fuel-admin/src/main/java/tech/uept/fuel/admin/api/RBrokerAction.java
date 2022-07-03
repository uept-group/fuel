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

    @RequestMapping("/list")
    public void list(@RequestBody HashMap<String, Object> map) {
        rBrokerService.updateBroker((Integer) map.get("id"), (String) map.get("brokerName"), (String) map.get("key"),
                (String) map.get("value"));
    }

}
