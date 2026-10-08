package com.libiao.lblearn1.common.utils;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlackList {

    private final Map<String,Long> blackList = new ConcurrentHashMap<>();

    public void addBlackList(String jti,long expireTime) {
        if(expireTime > System.currentTimeMillis()){
            blackList.put(jti,expireTime);
        }
    }

    public boolean isBlackList(String jti) {
        Long expireTime = blackList.get(jti);
        if(expireTime == null) return false;
        if(expireTime < System.currentTimeMillis()){
            blackList.remove(jti);
            return false;
        }
        return true;
    }
}
