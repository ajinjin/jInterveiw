package com.gu.thread.concurrent.cache;

import com.gu.mapper.SimpleDataMapper;
import com.gu.model.SimpleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SimpleCache {

//    @Autowired
    private SimpleDataMapper simpleDataMapper;

    private Map<String,String> simpleCacheMap = new ConcurrentHashMap<String, String>();
    ThreadLocal<String> tl = new ThreadLocal<String>();

    public void setTlValue(String v){
        tl.set(v);
    }

    @PostConstruct
    public void init(){
        //初始化缓存
        simpleCacheMap.put("1","1");
        simpleCacheMap.put("2","2");

    }

    //定时任务清空缓存
    @Scheduled(cron = "${gu.cache.cron}")
    public void refreshSimpleCacheMap(){
        simpleCacheMap = new ConcurrentHashMap<String, String>();
    }


    //springboot cache

    @Cacheable(cacheNames = {"getData"})
    public List<SimpleData> getSimpleData(){
        // 只有第一次访问数据库
        return simpleDataMapper.getData();
    }

    /**
     * ChachePut  更新缓存
     */
    public Boolean updateSimpleData(){
        Integer i = simpleDataMapper.updateData();
        return i > 0;
    }

    /**
     * 无论是否异常都清除缓存
     * @return
     */
    @CacheEvict(beforeInvocation = true)
    public boolean deleteSimpleCache() {
       return true;
    }


}
