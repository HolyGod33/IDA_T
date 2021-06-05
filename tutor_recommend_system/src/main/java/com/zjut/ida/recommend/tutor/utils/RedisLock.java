package com.zjut.ida.recommend.tutor.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * Redis 锁
 *
 * @author wly
 * @date 2021/5/17 21:12
 */
@Component
public class RedisLock {
    @Autowired
    private JedisPool jedisPool;

    private static final String LOCK_KEY = "redis_lock";
    private static final long INTERNAL_LOCK_LEASE_TIME = 120000;
    private static final long TIMEOUT = 120000;
    private static final SetParams PARAMS = SetParams.setParams().nx().px(INTERNAL_LOCK_LEASE_TIME);

    /**
     * 加锁
     */
    public boolean lock(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            long start = System.currentTimeMillis();
            for (; ; ) {
                // SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(LOCK_KEY, id, PARAMS);
                if ("OK".equals(lock)) {
                    return true;
                }
                // 否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long duration = System.currentTimeMillis() - start;
                if (duration >= TIMEOUT) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解锁
     */
    public boolean unlock(String id) {
        try (Jedis jedis = jedisPool.getResource()) {
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                    "   return redis.call('del',KEYS[1]) " +
                    "else" +
                    "   return 0 " +
                    "end";
            Object result = jedis.eval(script, Collections.singletonList(LOCK_KEY), Collections.singletonList(id));
            return "1".equals(result.toString());
        }
    }
}
