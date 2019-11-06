package org.chock.shop.dto;

import org.chock.shop.entity.User;

/**
 * @auther: zhuohuahe
 * @date: 2019/3/28 17:20
 * @description:
 */
public class UserInfo {

    public static ThreadLocal<User> userInfo = new ThreadLocal<>();

    public static void set(User user){
        userInfo.set(user);
    }

    public static User get(){
        return userInfo.get();
    }

    public static void clear(){
        userInfo.remove();
    }
}
