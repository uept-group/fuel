package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.apache.rocketmq.common.protocol.body.ProducerConnection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.domain.RProducerService;

@RestController
@RequestMapping("/rconsumer")
public class RProducerAction {

    @Resource
    private RProducerService rProducerService;

    @RequestMapping("/producerConnection")
    public Object producerConnection(@RequestParam(name = "nid") Integer id,
            @RequestParam(name = "producerGroup") String producerGroup, @RequestParam(name = "topic") String topic) {
        ProducerConnection producerConnection = rProducerService.producerConnection(id, producerGroup, topic);
        return producerConnection;
    }
}
