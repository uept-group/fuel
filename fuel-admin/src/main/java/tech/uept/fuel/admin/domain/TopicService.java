package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import tech.uept.fuel.admin.basic.mapper.TopicMapper;
import tech.uept.fuel.admin.basic.mapper.po.TopicPo;
import tech.uept.fuel.admin.basic.model.TopicModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;
import tech.uept.fuel.admin.basic.util.PageUtils;

@Service
public class TopicService {

    @Resource
    private TopicMapper topicMapper;

    public Object queryPage(int pageNo, int pageSize, String name) {
        QueryWrapper<TopicPo> query = new QueryWrapper<TopicPo>();
        query.like("name", name);
        return PageUtils.to(topicMapper.selectPage(new Page<TopicPo>(pageNo, pageSize), query));
    }

    public void insert(TopicModel topicModel) {
        TopicPo po = BeanUtils.getCopy(topicModel, new TopicPo());
        topicMapper.insert(po);
    }

    public void update(TopicModel topicModel) {
        TopicPo po = BeanUtils.getCopy(topicModel, new TopicPo());
        topicMapper.updateById(po);
    }

    public void deleteById(int id) {
        topicMapper.deleteById(id);
    }
}
