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

    @RequestMapping("/all")
    public Object queryAll(@RequestParam(name = "id") Integer id) {
        return rTopicService.queryAllTopic(id);
    }
}
