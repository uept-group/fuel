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
            @RequestParam(name = "pageSize") Integer pageSize,
            // name topic的名称，支持模糊搜索
            @RequestParam(name = "name") String name,
            // 类型，下拉框必选，分别是base普通队列，retry重试队列，dlq->DLQ队列，system系统队列
            @RequestParam(name = "type") String type) {
        return rTopicService.list(id, pageNo, pageSize, name, type);
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
