package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import tech.uept.fuel.admin.basic.mapper.ConsumerMapper;
import tech.uept.fuel.admin.basic.mapper.po.ConsumerPo;
import tech.uept.fuel.admin.basic.model.ConsumerModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;
import tech.uept.fuel.admin.basic.util.PageUtils;

@Service
public class ConsumerService {

    @Resource
    private ConsumerMapper consumerMapper;

    public Object queryPage(int pageNo, int pageSize, String name) {
        QueryWrapper<ConsumerPo> query = new QueryWrapper<ConsumerPo>();
        query.like("name", name);
        return PageUtils.to(consumerMapper.selectPage(new Page<ConsumerPo>(pageNo, pageSize), query));
    }

    public void insert(ConsumerModel consumerModel) {
        ConsumerPo po = BeanUtils.getCopy(consumerModel, new ConsumerPo());
        consumerMapper.insert(po);
    }

    public void update(ConsumerModel consumerModel) {
        ConsumerPo po = BeanUtils.getCopy(consumerModel, new ConsumerPo());
        consumerMapper.updateById(po);
    }

    public void deleteById(int id) {
        consumerMapper.deleteById(id);
    }
}
