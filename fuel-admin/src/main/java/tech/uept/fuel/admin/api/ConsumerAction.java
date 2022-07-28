package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.basic.model.ConsumerModel;
import tech.uept.fuel.admin.domain.ConsumerService;

@RestController
@RequestMapping("/topic")
public class ConsumerAction {

    @Resource
    private ConsumerService consumerService;

    @GetMapping("/findPage")
    public Object findPage(@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "name", defaultValue = "") String name) {
        return consumerService.queryPage(pageNo, pageSize, name);
    }

    @PostMapping("/add")
    public void add(@RequestBody ConsumerModel model) {
        consumerService.insert(model);
    }

    @PostMapping("/update")
    public void update(@RequestBody ConsumerModel model) {
        consumerService.update(model);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody ConsumerModel model) {
        consumerService.deleteById(model.getId());
    }
}
