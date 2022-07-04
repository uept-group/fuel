package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.RConsumerService;

@RestController
@RequestMapping("/rconsumer")
public class RConsumerAction {

    @Resource
    private RConsumerService rConsumerService;

    @RequestMapping("/list")
    public Object list(@RequestParam(name = "nid") Integer id) {
        return rConsumerService.list(id);
    }
}
