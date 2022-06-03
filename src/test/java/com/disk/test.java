package com.disk;

import org.junit.Test;

import java.io.File;

public class test {
    @Test
    public void test01(){
        File file = new File("D:\\config.md");

        file.delete();
    }
}
