package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicAction {

    @Resource
    private TopicService topicService;

    @RequestMapping("/query")
    public Object query() {
        return topicService.query();
    }

}
