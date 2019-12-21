package com.xjst.utils.pdfutils;

import com.lowagie.text.DocumentException;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

/**
 * PDF生成辅助类
 *
 * @author Goofy <a href="http://www.xdemo.org">http://www.xdemo.org</a>
 */
@SuppressWarnings("deprecation")
public class PdfHelper {

    public static ITextRenderer2 getRender() throws DocumentException, IOException {

        ITextRenderer render = new ITextRenderer();
        ITextRenderer2 renderer = new ITextRenderer2(render);
//        String path = getPath();
//        // 添加字体，以支持中文
//        renderer.getFontResolver().addFont(path + "simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        renderer.getFontResolver().addFont(path + "ARIALUNI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        renderer.getFontResolver().addFont(path + "KAITI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        renderer.getFontResolver().addFont(path + "SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return renderer;
    }

    // 获取要写入PDF的内容
    public static String getPdfContent(String ftlPath, String ftlName, Object o)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        return useTemplate(ftlPath, ftlName, o);
    }

    // 使用freemarker得到html内容
    public static String useTemplate(String ftlPath, String ftlName, Object o)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {

        String html = null;

        Template tpl = getFreemarkerConfig(ftlPath).getTemplate(ftlName);
        tpl.setEncoding("UTF-8");
        StringWriter writer = new StringWriter();
        tpl.process(o, writer);
        writer.flush();
        html = writer.toString();
        return html;
    }

    /**
     * 获取Freemarker配置
     *
     * @param templatePath
     * @return
     * @throws IOException
     */
    private static Configuration getFreemarkerConfig(String templatePath) throws IOException {
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File(templatePath));
        config.setEncoding(Locale.CHINA, "utf-8");
        return config;
    }

    /**
     * 获取类路径
     *
     * @return
     */
    public static String getPath() {
        return PdfHelper.class.getResource("/").getPath().substring(1);
    }

}
