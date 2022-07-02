package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.RTopicService;

@RestController
@RequestMapping("/rtopic")
public class RTopicAction {

    @Resource
    private RTopicService rTopicService;

    @RequestMapping("/list")
    public Object list(@RequestParam(name = "id") Integer id) {
        return rTopicService.list(id);
    }

    @RequestMapping("/status")
    public Object status(@RequestParam(name = "id") Integer id, @RequestParam(name = "topic") String topic) {
        return rTopicService.status(id, topic);
    }
}
