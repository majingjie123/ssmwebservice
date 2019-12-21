package com.xjst.utils.pdfutils;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class PDFBuilder extends PdfPageEventHelper {
    /**
     * Creates a new instance of PdfReportM1HeaderFooter 无参构造方法.
     */
    //设置患者基本信息
    private String content = null;

    public PDFBuilder() {
    }

    public PDFBuilder(String content) {
        this.content = content;
    }

    // 当当前页面开始加载时，触发(页眉)
    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        super.onStartPage(writer, document);
    }

    // 当当前页面初始化完成，切入document之前触发(页脚)
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        BaseFont font1 = null;
        BaseFont font2 = null;
        BaseFont font3 = null;
        try {
            String path = PdfHelper.getPath();
            //宋体
            font1 = BaseFont.createFont(path + "simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //楷体
            font2 = BaseFont.createFont(path + "KAITI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            //黑体
            font3 = BaseFont.createFont(path + "simhei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3号楷体
        Font fontDetail2 = new Font(font2, 16F, Font.NORMAL);
        ///5号宋体
        Font font = new Font(font1, 10.5F, Font.NORMAL);
        //2号黑体
        Font fontDetail3 = new Font(font3, 22F, Font.NORMAL);

        Rectangle rect = new Rectangle(0, 30, 50, 50);
        //第一根横线
        setUnderline(25, 640, 500, 640, 1, writer);
        //第二根横线
        setUnderline(25, 22, 500, 22, 1, writer);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP,
                new Phrase(String.format("%s", "南 京 鼓 楼 医 院"), fontDetail2), 200,
                708, 0);
        //设置标题
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP,
                new Phrase(String.format("%s", "南京大学医学院附属鼓楼医院"), fontDetail2), 165,
                690, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_TOP,
                new Phrase(String.format("%s", "血液净化治疗记录单"), fontDetail3), 175,
                665, 0);
        //设置患者基本信息

        //显示页脚
        String pageIndex = "第 " + writer.getPageNumber() + " 页";
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_BOTTOM,
                new Phrase(String.format("%s", pageIndex), font), 245,
                rect.getBottom() - 20, 0);
    }

    /**
     * 画出对应的下划线
     *
     * @param stratX
     * @param stratY
     * @param endX
     * @param endY
     * @param writer
     */
    private static void setUnderline(float stratX, float stratY, float endX, float endY, float widthLine,
                                     PdfWriter writer) {
        PdfContentByte cd = writer.getDirectContent();
        //设置线宽
        cd.setLineWidth(widthLine);
        //设置线条起点的坐标
        cd.moveTo(stratX, stratY);
        //设置线条终点的坐标
        cd.lineTo(endX, endY);
        //使用画笔画线条
        cd.stroke();
    }
}