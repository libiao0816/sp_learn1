package com.libiao.lblearn1.common.context;

import com.libiao.lblearn1.domain.po.User;
import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private final static ThreadLocal<User> ThreadLocalUser = new ThreadLocal<User>();

    public static User getUser() {
       return ThreadLocalUser.get();
    }

    public static void setUser(User user) {
        ThreadLocalUser.set(user);
    }

    public static void removeUser() {ThreadLocalUser.remove();}

}
