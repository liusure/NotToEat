package com.notoeat.core.test;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by l on 2017/5/20.
 */
@Service
public class EhcacheTestService {
    @Cacheable(value="cacheTest",key="#param")
    public String cacheTest(String param){
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
}
