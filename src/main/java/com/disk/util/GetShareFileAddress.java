package com.disk.util;

import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class GetShareFileAddress {
    public static String getAddress(){
        return ("--" + UUID.randomUUID() + System.currentTimeMillis() + "--");
    }

    @Test
    public void test(){
        System.out.println(new GetShareFileAddress().getAddress());
    }
}
