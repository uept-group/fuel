package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.RTopicService;

@RestController
@RequestMapping("/rtopic")
public class RTopicAction {

    @Resource
    private RTopicService rTopicService;

    @GetMapping("/list")
    public Object list(@RequestParam(name = "nid") Integer id) {
        return rTopicService.list(id);
    }

    @GetMapping("/findByPage")
    public Object findByPage(@RequestParam(name = "nid") Integer id, @RequestParam(name = "pageNo") Integer pageNo,
            @RequestParam(name = "pageSize") Integer pageSize, @RequestParam(name = "name") String name) {
        return rTopicService.list(id, pageNo, pageSize);
    }

    @GetMapping("/status")
    public Object status(@RequestParam(name = "nid") Integer id, @RequestParam(name = "topic") String topic) {
        return rTopicService.status(id, topic);
    }

    @GetMapping("/route")
    public Object route(@RequestParam(name = "nid") Integer id, @RequestParam(name = "topic") String topic) {
        return rTopicService.route(id, topic);
    }

    @GetMapping("/consumer")
    public Object consumer(@RequestParam(name = "nid") Integer id, @RequestParam(name = "topic") String topic) {
        return rTopicService.consumer(id, topic);
    }

}
