package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.uept.fuel.admin.domain.RMessageService;

@RestController
@RequestMapping("/rmessage")
public class RMessageAction {

    @Resource
    private RMessageService rMessageService;

    @RequestMapping("/queryByKey")
    public Object queryByKey(@RequestParam(name = "nid") Integer id, @RequestParam(name = "topic") String topic,
            @RequestParam(name = "key") String key) {
        return rMessageService.queryByKey(id, topic, key);
    }
}
