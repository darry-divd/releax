package com.example.demo.normal;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
public class testExcel {

    public static void main(String[] args) {
        String filePath = "src/main/resources/aaa.xls";
        String[] title ={"姓名","年龄","性别","住址"};

        String[] context ={"张三,23,男,吉林","李四,24,男,通辽","王琦,23,女,吉林",""};

    }



    /** 写excel文件  默认高度 16
     * @author guancong
     * @date 2022/4/1
     * @param  path  文件存储路径
     * @param  name  文件名称
     * @param  sheetName  sheet页 不传 默认1
     * @param  title  首行 String数组
     * @param  context  文件 String数组 需要保证顺序和首行一致
     * @return
     * @version  1.0
     */
    public void writeExcel(String path,String name,String sheetName ,String[] title,String[] context){
        Workbook wb = new XSSFWorkbook();
        CellStyle cellStyle = wb.createCellStyle();
        Font fontStyle = wb.createFont();
        //创建表
        Sheet sheet = wb.createSheet(sheetName);
        //设置默认列宽
        sheet.setDefaultColumnWidth(20);

        writeExcelTitle(sheet,cellStyle,title,fontStyle);
        writeExcelcontext(sheet,cellStyle,context,fontStyle);
        try{
            OutputStream out = new FileOutputStream(new File(path));
            wb.write(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeExcelcontext(Sheet sheet, CellStyle cellStyle, String[] context,Font fontStyle) {
        for (int i = 0; i < context.length; i++) {
            //需要跳过首行
            Row row = sheet.createRow(i+1);
            String s = context[i];
            String[] split = s.split(",");
            for (int j = 0; j <split.length ; j++) {
                Cell firstCell = row.createCell(j);
                fontStyle.setFontHeightInPoints((short)16);
                //将字体应用到单元格
                cellStyle.setFont(fontStyle);
                firstCell.setCellStyle(cellStyle);
                firstCell.setCellValue(split[i]);
            }
        }
    }

    public void writeExcelTitle(Sheet sheet,CellStyle cellStyle,String[] title, Font fontStyle) {
        Row fristRow = sheet.createRow(0);
        for (int i = 0; i < title.length; i++) {
            Cell firstCell = fristRow.createCell(i);
            fontStyle.setFontHeightInPoints((short)16);
            //将字体应用到单元格
            cellStyle.setFont(fontStyle);
            firstCell.setCellStyle(cellStyle);
            firstCell.setCellValue(title[i]);
        }
    }


}
