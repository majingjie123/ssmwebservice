package com.xjst.test;

import com.xjst.utils.FileUtils;


import java.io.File;

public class TestFile {


    public void downFile() throws Exception {
        String parth = "C:\\Users\\Administrator\\Desktop\\timg (1).jpg";
        String dexc = "F:\\mm.bmp";
        boolean b = FileUtils.fileMove(parth, dexc);
        System.out.println(b);
    }
}
