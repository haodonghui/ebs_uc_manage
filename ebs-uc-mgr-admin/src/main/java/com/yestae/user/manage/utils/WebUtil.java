package com.yestae.user.manage.utils;

/**
 * @Package com.yestae.user.manage.utils
 * @ClassName
 * @Author h_don
 * @Date 2020/4/14 16:40
 */

public class WebUtil {
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
