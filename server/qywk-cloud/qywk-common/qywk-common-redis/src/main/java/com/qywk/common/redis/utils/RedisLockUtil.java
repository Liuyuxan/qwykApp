package com.qywk.common.redis.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author cxq
 * @date 2024/2/21
 * @description 使用redis实现的简单分布式锁，此为cxq手搓的，方便使用boot工具故注册为组件，不是最佳实践，望后来的大佬能够自行修改
 */
@Component
public class RedisLockUtil {

    /**
     * 不断尝试获取锁时的最大等待时间，单位: 毫秒
     * */
    private static final long maxGetLockTime = 15_000; // 15s

    public class Lock{

        /**
         * 锁的名称，也是定位锁的唯一标识
         * */
        private String lockName;

        /**
         * 用于区分加锁者的uuid，防止释放掉其他线程/服务的锁
         * */
        private String uuid;

        /**
         * 释放这个锁的方法，务必在业务结束后调用！
         * @return true: 释放成功, false: 释放失败（可能是由于锁已经自动过期释放）
         * */
        public boolean delete() {
            return deleteLock(this);
        }


        private Lock(String lockName, String uuid) {
            this.lockName = lockName;
            this.uuid = uuid;
        }

    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 不断尝试获取名为lockName的锁，直到获取超时或成功获取到锁，并返回<br>
     * 如果获取到了，则返回一个Lock实例，否则返回null<br>
     * @param lockName 锁的名字，也是锁的唯一标识
     * @param autoExpireTime 自动释放锁的时间
     * @param timeUnit 时间的单位类型枚举
     * */
    public Lock lockUntilGet(String lockName, long autoExpireTime, TimeUnit timeUnit) {

        long startTime = System.currentTimeMillis(); // 记录开始获取锁的时间
        Lock lock = tryToGetLock(lockName, autoExpireTime, timeUnit); // 尝试单次获取锁

        while (lock == null) {

            // lock为null则代表获取锁失败（大概率其他线程服务占用着），判断是否超时再重新尝试获取
            if (System.currentTimeMillis() - startTime > maxGetLockTime) {
                return null; // 超时，获取锁失败
            }

            lock = tryToGetLock(lockName, autoExpireTime, timeUnit); // 尝试单次获取锁

            if (lock == null) try { // 获取失败，等待0.2s再次轮询，以防止占用过多CPU
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        return lock;
    }

    /**
     * 尝试获取一次名为lockName的锁，并返回<br>
     * 如果获取到了，则返回一个Lock实例，否则返回null<br>
     * @param lockName 锁的名字，也是锁的唯一标识
     * @param autoExpireTime 自动释放锁的时间
     * @param timeUnit 时间的单位类型枚举
     * */
    public Lock tryToGetLock(String lockName, long autoExpireTime, TimeUnit timeUnit) {

        // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 尝试获取锁……");

        String uuid = UUID.randomUUID().toString(); // 锁设置者的唯一标识

        // 尝试获取锁，就是进行一次redis setNx操作
        lockName = "redis_lock:" + lockName; // 重新设置lockName防止redis key冲突
        Boolean bool = redisTemplate.opsForValue().setIfAbsent(lockName, uuid, autoExpireTime, timeUnit);

        if (bool == null || !bool) {
            // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 获取锁失败");
            return null; // 锁获取失败
        }

        // 获取成功
        Lock lock = new Lock(lockName, uuid);

        // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 获取锁成功");
        return lock;
    }

    /**
     * 释放某个锁的方法
     * @return true: 释放成功, false: 释放失败（可能是由于锁已经自动过期释放）
     * */
    public boolean deleteLock(Lock lock) {

        // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 正在释放锁");

        // 操作①
        String uuid = String.valueOf(redisTemplate.opsForValue().get(lock.lockName));

        if (uuid.equals(lock.uuid)) {
            // 操作② uuid 正确，即可释放掉这个锁
            // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 释放锁成功");
            return redisTemplate.delete(lock.lockName);
        }

        /*
        * 由于上述操作①的get与下面操作②的delete不是一个原子操作，所以仍然有可能出现（虽然概率低）释放掉别人的锁等线程问题
        * 所以本分布式锁方案并非完美，不止于此，可能在其他地方仍有缺陷，不过基本上能胜任目前4.0的需求（问为什么就是我猜的）
        * */
        // LoggerFactory.getLogger(getClass()).info("线程: " + Thread.currentThread().getId() + " 释放锁失败");
        return false;
    }

}
