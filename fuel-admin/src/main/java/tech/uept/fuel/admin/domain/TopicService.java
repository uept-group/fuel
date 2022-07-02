package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.mapper.TopicMapper;
import tech.uept.fuel.admin.basic.mapper.po.TopicPo;
import tech.uept.fuel.admin.basic.model.TopicModel;
import tech.uept.fuel.admin.basic.util.BeanUtils;

@Service
public class TopicService {

    @Resource
    private TopicMapper topicMapper;

    public Object query() {
        return topicMapper.selectCount(null);
    }

    public void insert(TopicModel topicModel) {
        
        
        
        TopicPo po = BeanUtils.getCopy(topicModel, new TopicPo());
        topicMapper.insert(po);
    }
}
