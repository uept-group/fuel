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

    public List<String> list(Integer id) {
        List<String> list = localCache.get(CACHE_KEY_PRE + id);
        if (list != null) {
            return list;
        }
        String nAdddr = namesrvService.getAddrById(id);
        list = client.consumerList(nAdddr);
        localCache.put(CACHE_KEY_PRE + id, list, 1000 * 60 * 5);
        return list;
    }

    public Object findPage(Integer id, Integer pageNo, Integer pageSize, String name) {
        List<String> list = this.list(id);
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
}
