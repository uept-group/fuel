package tech.uept.fuel.admin.domain;

import java.util.ArrayList;
import java.util.List;

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

    private static final String CACHE_KEY_PRE = "consumer:";

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
        return CACHE_KEY_PRE + id;
    }

    public Object findPage(Integer namesrvId, Integer pageNo, Integer pageSize, String name) {
        List<String> list = this.list(namesrvId);
        if (StringUtils.isNotBlank(name)) {
            List<String> list2 = new ArrayList<>();
            for (String s : list) {
                if (s.contains(name)) {
                    list2.add(s);
                }
            }
            list = list2;
        }
        MyPage page = PageUtils.listToPage(list, pageNo, pageSize);
        return page;
    }

    public Object findConfig(Integer id, String name) {
        String nAdddr = namesrvService.getAddrById(id);
        return client.consumerConfig(name, nAdddr);
    }
}
