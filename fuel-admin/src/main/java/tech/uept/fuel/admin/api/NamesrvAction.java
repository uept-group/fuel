package tech.uept.fuel.admin.api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.domain.NamesrvService;

@RestController
@RequestMapping("/namesrv")
public class NamesrvAction {

    @Resource
    private NamesrvService namesrvService;

    @PostMapping("/insert")
    public void insert(@RequestBody NamesrvModel namesrvModel) {
        namesrvService.insert(namesrvModel);
    }

    @PostMapping("/update")
    public void update(@RequestBody NamesrvModel namesrvModel) {
        namesrvService.update(namesrvModel);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody NamesrvModel namesrvModel) {
        namesrvService.delete(namesrvModel.getId());
    }

    @GetMapping("/all")
    public Object all() {
        return namesrvService.all();
    }
}
