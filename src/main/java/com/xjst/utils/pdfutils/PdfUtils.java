package com.xjst.utils.pdfutils;

import com.lowagie.text.DocumentException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * PDF生成工具类
 *
 * @author Goofy <a href="http://www.xdemo.org">http://www.xdemo.org</a>
 */
public class PdfUtils {

    public static void main(String[] args) {
        try {

            Map<Object, Object> o = new HashMap<Object, Object>();
            o.put("name", "http://www.xdemo.org/");

            String path = PdfHelper.getPath();

            generateToFile(path, "xx.ftl", path, o, "D:/xw.pdf", "");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 生成PDF到文件
     *
     * @param ftlPath       模板文件路径（不含文件名）
     * @param ftlName       模板文件吗（不含路径）
     * @param imageDiskPath 图片的磁盘路径
     * @param data          数据
     * @param outputFile    目标文件（全路径名称）
     * @throws Exception
     */
    public static void generateToFile(String ftlPath, String ftlName, String imageDiskPath, Object data, String outputFile, String htmlTemp) throws Exception {
        Map<String, Object> dataMap = (Map<String, Object>) data;
        Map<Object, Object> content = (Map<Object, Object>) dataMap.get("content");

        String ptType = (String) content.get("ptType");
        String name = (String) content.get("name");
        String bedCode = (String) content.get("bedCode");
        //生成固定的标题类
//        name = StringUtil.isNotEmpty(name) ? name : "";//患者姓名
//        bedCode = StringUtil.isNotEmpty(bedCode) ? bedCode : "";//住院号：门诊号
//        StringBuilder stringBuilder = new StringBuilder();
//        if (StringUtil.isNotEmpty(ptType) && Objects.equals("1", ptType)) {
//
//            stringBuilder.append("门诊：门诊");
//            stringBuilder.append("                            姓名：" + name);
//            stringBuilder.append("                            住院号：" + bedCode);
//        }
//        String info = StringUtil.isNotEmpty(stringBuilder.toString()) ? stringBuilder.toString() : "";
        String html = PdfHelper.getPdfContent(ftlPath, ftlName, data);
        html = html.replace("&nbsp;", " ");
        html = html.replace("<br>", "<br/>");
        OutputStream out = null;
        ITextRenderer2 render = null;
        out = new FileOutputStream(outputFile);
        render = PdfHelper.getRender();
//        render.setPdfPageEvent(new PDFBuilder(info));
        render.getPdfPageEvent();
        render.setDocumentFromString(html);
        if (imageDiskPath != null && !imageDiskPath.equals("")) {
            // html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
            render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
        }
        render.layout();
        render.createPDF(out, true);
        render.finishPDF();
        render = null;
        out.close();
    }

    /**
     * 生成PDF到输出流中（ServletOutputStream用于下载PDF）
     *
     * @param ftlPath       ftl模板文件的路径（不含文件名）
     * @param ftlName       ftl模板文件的名称（不含路径）
     * @param imageDiskPath 如果PDF中要求图片，那么需要传入图片所在位置的磁盘路径
     * @param data          输入到FTL中的数据
     * @param response      HttpServletResponse
     * @return
     * @throws TemplateNotFoundException
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     * @throws DocumentException
     */
    public static OutputStream generateToServletOutputStream(String ftlPath, String ftlName, String imageDiskPath, Object data,
                                                             HttpServletResponse response) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException, DocumentException {
        String html = PdfHelper.getPdfContent(ftlPath, ftlName, data);
        OutputStream out = null;
        ITextRenderer2 render = null;
        out = response.getOutputStream();
        render = PdfHelper.getRender();
        render.setDocumentFromString(html);
        if (imageDiskPath != null && !imageDiskPath.equals("")) {
            // html中如果有图片，图片的路径则使用这里设置的路径的相对路径，这个是作为根路径
            render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
        }
        render.layout();
        render.createPDF(out);
        render.finishPDF();
        render = null;
        return out;
    }

    /**
     * 打开pdf
     *
     * @param pathAndFileName
     * @param request
     * @param response
     * @return
     */
    public static String openPdfFile(String pathAndFileName, HttpServletRequest request, HttpServletResponse response) {
        File file = new File(pathAndFileName);
        if (!file.exists()) {
            //  return "/error";
            throw new RuntimeException();
        }
        InputStream in = null;
        OutputStream os = null;
        try {
            response.setContentType("application/pdf"); // 设置返回内容格式
            in = new FileInputStream(file); // 用该文件创建一个输入流
            os = response.getOutputStream(); // 创建输出流
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        } catch (Exception e) {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
