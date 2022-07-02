package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.domain.NamesrvService;

@RestController
@RequestMapping("/namesrv")
public class NamesrvAction {

    @Resource
    private NamesrvService namesrvService;

    @RequestMapping("/insert")
    public void insert(NamesrvModel namesrvModel) {
        namesrvService.insert(namesrvModel);
    }

    @RequestMapping("/update")
    public void update(NamesrvModel namesrvModel) {
        namesrvService.update(namesrvModel);
    }

    @RequestMapping("/all")
    public Object all() {
        return namesrvService.all();
    }
}
