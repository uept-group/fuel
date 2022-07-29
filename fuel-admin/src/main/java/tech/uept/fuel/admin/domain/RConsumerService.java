package tech.uept.fuel.admin.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import tech.uept.fuel.admin.basic.cache.LocalCache;
import tech.uept.fuel.admin.basic.model.MyPage;
import tech.uept.fuel.admin.basic.rmq.RocketmqComplexClient;
import tech.uept.fuel.admin.basic.util.PageUtils;

@Service
public class RConsumerService {

    @Resource
    private NamesrvService namesrvService;

    @Resource
    private RocketmqComplexClient client;

    @Resource
    private LocalCache localCache;

    public List<String> list(Integer namesrvId) {
        List<String> list = localCache.get(genKey(namesrvId));
        if (list != null) {
            return list;
        }
        String nAdddr = namesrvService.getAddrById(namesrvId);
        list = client.consumerList(nAdddr);
        localCache.put(genKey(namesrvId), list, 1000 * 60 * 5);
        return list;
    }

    public String genKey(Integer id) {
        return "consumer:" + id;
    }

    public MyPage findPage(Integer namesrvId, Integer pageNo, Integer pageSize, String name) {
        List<String> list = this.list(namesrvId);
        if (StringUtils.isNotBlank(name)) {
            list = list.stream().filter(s -> s.contains(name)).collect(Collectors.toList());
        }
        MyPage page = PageUtils.listToPage(list, pageNo, pageSize);
        return page;
    }

    public Object findConfig(Integer id, String name) {
        String nAdddr = namesrvService.getAddrById(id);
        return client.consumerConfig(name, nAdddr);
    }
}
