package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.basic.model.TopicModel;
import tech.uept.fuel.admin.domain.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicAction {

    @Resource
    private TopicService topicService;

    @RequestMapping("/query")
    public Object query(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "name", defaultValue = "") String name) {
        return topicService.queryPage(pageNo, pageSize, name);
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody TopicModel topicModel) {
        topicService.insert(topicModel);
    }

    @RequestMapping("/update")
    public void update(@RequestBody TopicModel topicModel) {
        topicService.update(topicModel);
    }
}
