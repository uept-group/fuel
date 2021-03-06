package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/findPage")
    public Object findPage(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "name", defaultValue = "") String name) {
        return topicService.queryPage(pageNo, pageSize, name);
    }

    @PostMapping("/add")
    public void add(@RequestBody TopicModel topicModel) {
        topicService.insert(topicModel);
    }

    @PostMapping("/update")
    public void update(@RequestBody TopicModel topicModel) {
        topicService.update(topicModel);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody TopicModel topicModel) {
        topicService.deleteById(topicModel.getId());
    }
}
