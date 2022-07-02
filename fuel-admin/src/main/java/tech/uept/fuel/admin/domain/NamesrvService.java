package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.mapper.NamesrvMapper;
import tech.uept.fuel.admin.basic.mapper.po.NamesrvPo;
import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;

@Service
public class NamesrvService {

    @Resource
    private NamesrvMapper namesrvMapper;

    public void insert(NamesrvModel namesrvModel) {
        NamesrvPo po = BeanUtils.getCopy(namesrvModel, new NamesrvPo());
        namesrvMapper.insert(po);
    }

}
