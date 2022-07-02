package tech.uept.fuel.admin.basic.util;

public class BeanUtils {

    public static <T> T getCopy(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
        return (T) target;
    }
}
