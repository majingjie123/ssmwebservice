package com.xjst.utils.fileutils;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具类
 */
public class FileUtils {
    /**
     *
     * @param path  文件下载源路径 c://a.txt
     * @param filename 文件名字 可有可无
     * @param request
     * @param response
     * @throws Exception
     */
    public static void downFile(String path,String filename,HttpServletRequest request ,HttpServletResponse response) throws Exception {
        File file = new File(path);
        //判断文件是否存在
        if (!file.exists()){
            return;
        }
        String fileName = file.getName();
        if (filename.trim() != null && filename.trim().length() > 0){
            if (filename.lastIndexOf(".") == -1){
                int i = fileName.lastIndexOf(".");
                String lastPreName = fileName.substring(i, fileName.length());
                filename = filename + lastPreName;
            }
        }
        InputStream in = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(in);
        String encoding = request.getCharacterEncoding();
        //设置响应头，控制浏览器下载该文件
        response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(filename, encoding));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024*8];
        int index = -1;
        while ((index=bis.read(bytes))!=-1){
            outputStream.write(bytes,0,index);
        }
        outputStream.flush();
        outputStream.close();
        bis.close();
        in.close();
    }

    /**
     *
     * @param paths 文件路径
     * @param zipNmme 将所有需要下载的文件压缩为zip
     * @param request
     * @param response
     * @return 将不存在文件路径返回list
     * @throws Exception
     */
    public static List<String> BachFileDown(String[] paths, String zipNmme, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String encoding = request.getCharacterEncoding();
        zipNmme = URLEncoder.encode(zipNmme, encoding);//转换中文否则可能会产生乱码
        response.setContentType("application/octet-stream");// 指明response的返回对象是
        response.setHeader("Content-Disposition", "attachment;filename=" + zipNmme);// 设置在下载框默认显示的文件名
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        List<String> noFile = new ArrayList<String>();//将不存在的文件路径添加进去
        List<String> existsFile = new ArrayList<String>();//添加已经添加到压缩包的路径
        if (paths != null && paths.length > 0){
            for (String path : paths) {
                File file = new File(path);
                if (!file.exists()){//文件不存在进行下个文件操作
                    noFile.add(path);
                    continue;
                }
                if (existsFile != null && !existsFile.isEmpty() && existsFile.contains(path)){//文件已经添加到了zip
                    continue;
                }
                if (!file.canRead()){//文件是否可以读取
                    continue;
                }
                existsFile.add(path);
                FileInputStream inputStream = new FileInputStream(file);
                BufferedInputStream buinstream = new BufferedInputStream(inputStream);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024*8];
                int index = -1;
                while ((index = buinstream.read(bytes)) !=-1){
                    zos.write(bytes,0,index);
                }
                buinstream.close();
                zos.closeEntry();
                inputStream.close();
            }
            zos.finish();
            zos.close();
        }
        return noFile;
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @param desc
     * @throws Exception
     */
    public static void fileUpload(MultipartFile file,HttpServletRequest request,String desc) throws Exception {
        InputStream in = file.getInputStream();
        String filename = file.getOriginalFilename();
        BufferedInputStream bis = new BufferedInputStream(in);
        OutputStream os = new FileOutputStream(desc+"\\"+filename);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        int index = -1;
        byte[] bytes = new byte[1024*8];
        while ((index = bis.read(bytes)) != -1){
            bos.write(bytes,0,index);
        }
        bos.flush();
        bos.close();
        os.flush();
        os.close();
        bis.close();
        in.close();
    }
    /**
     * 文件上传
     * @param file
     * @param request
     * @param desc
     * @throws Exception
     */
    public static void bachFileUpload(MultipartFile[] files,HttpServletRequest request,String desc) throws Exception {
        for (MultipartFile file : files) {
            InputStream in = file.getInputStream();
            String filename = file.getOriginalFilename();
            BufferedInputStream bis = new BufferedInputStream(in);
            OutputStream os = new FileOutputStream(desc+"\\"+filename);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            int index = -1;
            byte[] bytes = new byte[1024*8];
            while ((index = bis.read(bytes)) != -1){
                bos.write(bytes,0,index);
            }
            bos.flush();
            bos.close();
            os.flush();
            os.close();
            bis.close();
            in.close();
        }
    }
    /**
     *
     * @param path 文件源目录
     * @param desc 文件拷贝目录
     *             成功为 true 失败为 false
     */
    public static boolean fileCopy(String path,String desc) throws Exception {
        File file = new File(path);
        if (!file.exists()||!file.canRead()){
            return false;
        }
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        OutputStream outputStream = new FileOutputStream(new File(desc));
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        byte[] bytes = new byte[1024*8];
        int index = -1;
        while ((index = bis.read(bytes))!=-1){
            bos.write(bytes,0,index);
        }
        bos.flush();
        bos.close();
        outputStream.flush();
        outputStream.close();
        bis.close();
        inputStream.close();
        return true;
    }

    /**
     * 文件移动
     * @param path
     * @param desc
     * @return
     * @throws Exception
     */
    public static boolean fileMove(String path,String desc) throws Exception {
        File file = new File(path);
        if (!file.exists()||!file.canRead()){
            return false;
        }
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        OutputStream outputStream = new FileOutputStream(new File(desc));
        BufferedOutputStream bos = new BufferedOutputStream(outputStream);
        byte[] bytes = new byte[1024*8];
        int index = -1;
        while ((index = bis.read(bytes))!=-1){
            bos.write(bytes,0,index);
        }
        bos.flush();
        bos.close();
        outputStream.flush();
        outputStream.close();
        bis.close();
        inputStream.close();
        file.delete();
        return true;
    }
}
