package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.basic.model.MessagePage;
import tech.uept.fuel.admin.basic.model.MessageQuery;
import tech.uept.fuel.admin.domain.RMessageService;

@RestController
@RequestMapping("/rmessage")
public class RMessageAction {

    @Resource
    private RMessageService rMessageService;

    @RequestMapping("/findByKey")
    public Object queryByKey(@RequestParam(name = "nid") Integer nid, @RequestParam(name = "topic") String topic,
            @RequestParam(name = "key") String key) {
        return rMessageService.queryByKey(nid, topic, key);
    }

    @PostMapping("/queryMessagePageByTopic.query")
    @ResponseBody
    public MessagePage queryMessagePageByTopicold(@RequestBody MessageQuery query) {
        return rMessageService.queryMessageByPage(query);
    }

    @GetMapping("/findByTime")
    @ResponseBody
    public MessagePage findByTime(int nid, String topic, long startTime, long endTime) {
        return rMessageService.findByTime(nid, topic, startTime, endTime);
    }
}
