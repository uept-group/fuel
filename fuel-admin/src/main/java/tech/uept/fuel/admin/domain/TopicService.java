package tech.uept.fuel.admin.domain;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.mapper.TopicMapper;

@Service
public class TopicService {

    @Resource
    private TopicMapper topicMapper;

    public Object query() {
        return topicMapper.selectCount(null);
    }

}
