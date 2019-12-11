package com.ruoyi.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xieqiang
 * Date: 2015/12/9
 * Time: 11:10
 */
public class ExcelExportUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtil.class);

    private ExcelExportUtil() {
    }

    public static class Header {

        String title;

        int width;

        public Header(String t, int w) {
            title = t;
            width = w;
        }
    }

    /**
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     */
    public static void exportExcel(String title, List<Header> headers,
                                   List<List> dataset, OutputStream out) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFPalette palette = workbook.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.LIGHT_GREEN.index, (byte) 204, (byte) 232, (byte) 207);
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);

        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);

        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);

        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setWrapText(true);
        style2.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.LEFT);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);


        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Header header = headers.get(i);
            sheet.setColumnWidth(i, header.width * 256);
            HSSFCell cell = row.createCell(i);

            cell.setCellStyle(style);
            cell.setCellValue(header.title);
        }

        // 遍历集合数据，产生数据行
        for (int index = 0; index < dataset.size(); index++) {
            row = sheet.createRow(index + 1);
            List data = dataset.get(index);

            for (int i = 0; i < data.size(); i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);

                Object value = data.get(i);
                if (value == null) {
                    value = "";
                }
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                } else if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);

                } else {
                    cell.setCellValue(value.toString());
                }

            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}
