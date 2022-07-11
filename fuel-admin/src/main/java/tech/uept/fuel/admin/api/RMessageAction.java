package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

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

    @RequestMapping("/queryByKey")
    public Object queryByKey(@RequestParam(name = "nid") Integer id, @RequestParam(name = "topic") String topic,
            @RequestParam(name = "key") String key) {
        return rMessageService.queryByKey(id, topic, key);
    }

    @PostMapping("/queryMessagePageByTopic.query")
    @ResponseBody
    public MessagePage queryMessagePageByTopic(@RequestBody MessageQuery query) {
        return rMessageService.queryMessageByPage(query);
    }
}
