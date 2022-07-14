package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.exp.BizException;
import tech.uept.fuel.admin.basic.mapper.NamesrvMapper;
import tech.uept.fuel.admin.basic.mapper.po.NamesrvPo;
import tech.uept.fuel.admin.basic.model.NamesrvModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;

@Service
public class NamesrvService {

    @Resource
    private NamesrvMapper namesrvMapper;

    public void insert(NamesrvModel namesrvModel) {
        this.check(namesrvModel);
        NamesrvPo po = BeanUtils.getCopy(namesrvModel, new NamesrvPo());
        namesrvMapper.insert(po);
    }

    public void update(NamesrvModel namesrvModel) {
        this.check(namesrvModel);
        NamesrvPo po = BeanUtils.getCopy(namesrvModel, new NamesrvPo());
        namesrvMapper.updateById(po);
    }

    public List<NamesrvModel> all() {
        List<NamesrvPo> listPo = namesrvMapper.selectList(null);
        List<NamesrvModel> listModel = new ArrayList<>();
        for (NamesrvPo po : listPo) {
            listModel.add(BeanUtils.getCopy(po, new NamesrvModel()));
        }
        return listModel;
    }

    public String getAddrById(Integer id) {
        return namesrvMapper.selectById(id).getAddr();
    }

    public void delete(Integer id) {
        namesrvMapper.deleteById(id);
    }

    public void check(NamesrvModel namesrvModel) {
        if (namesrvModel == null) {
            throw new BizException("param cant empty");
        }
        namesrvModel.setAddr(StringUtils.trim(namesrvModel.getAddr()));
        String addr = namesrvModel.getAddr();
        if (StringUtils.isEmpty(addr)) {
            throw new BizException("addr cant empty");
        }
        if (addr.contains(",") || addr.contains("，")) {
            throw new BizException("separator is ';', eg 'ip:port;ip:port'");
        }
    }
}
