package tech.uept.fuel.admin.basic.util;

import org.apache.rocketmq.common.message.MessageExt;

import tech.uept.fuel.admin.basic.model.QueryMessage;

public class BeanUtils {

    public static <T> T getCopy(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return (T) target;
    }

    public static QueryMessage msgToMsg(MessageExt messageExt) {
        return new QueryMessage();
    }
}
