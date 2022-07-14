package tech.uept.fuel.admin.basic.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.rocketmq.common.message.MessageExt;

import tech.uept.fuel.admin.basic.mapper.po.BrokerConfigPo;
import tech.uept.fuel.admin.basic.model.QueryMessage;

public class BeanUtils {

    public static <T> T getCopy(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return (T) target;
    }

    public static QueryMessage msgToMsg(MessageExt messageExt) {
        return new QueryMessage();
    }

    public static Map<String, BrokerConfigPo> listToMap(List<BrokerConfigPo> list) {
        Map<String, BrokerConfigPo> map = new HashMap<>();
        for (BrokerConfigPo po : list) {
            map.put(po.getKey(), po);
        }
        return map;
    }
}
