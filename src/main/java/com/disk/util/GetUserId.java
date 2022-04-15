package com.disk.util;

import java.util.UUID;

public class GetUserId {
    public String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
