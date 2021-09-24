package com.bjpowernode;

import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

public class MyTest {

    //测试 MD5 加密工具：
    @Test
    public void testMD5(){
        //假设 000000 是密码。
        String md5 = MD5Util.getMD5("000000");
        //加密后的数据。
        System.out.println(md5);
    }

}
