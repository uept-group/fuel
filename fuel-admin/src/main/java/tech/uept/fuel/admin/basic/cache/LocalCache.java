package tech.uept.fuel.admin.basic.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class LocalCache {

    private static Map<String, CacheNode> cache = new ConcurrentHashMap<String, CacheNode>();

    /**
     * @param key
     * @param data
     * @param time 毫秒
     */
    public void put(String key, Object data, long time) {
        time = System.currentTimeMillis() + time;
        CacheNode cacheNode = new CacheNode();
        cacheNode.setTime(time);
        cacheNode.setData(data);
        cache.put(key, cacheNode);
    }

    public <T> T get(String key) {
        CacheNode cacheNode = cache.get(key);
        if (cacheNode == null) {
            return null;
        }
        if (System.currentTimeMillis() > cacheNode.getTime()) {
            return null;
        }
        return (T) cacheNode.getData();
    }

    static class CacheNode {

        private long time;

        private Object data;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
