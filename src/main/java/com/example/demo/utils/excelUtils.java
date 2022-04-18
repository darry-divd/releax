package com.example.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述  excel 读写工具类
 *
 * @author guacnong
 * @date $
 */
public class excelUtils {

    
    
    public List<Map<String,String>> readExcel(String  filePath){
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        // tring filePath = "C:\\Users\\guancong\\Desktop\\2222.xlsx";
        String columns[] = {"name","age","score"};
        wb = getWorkBook(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    cellData = (String) getCellFormatValue(row.getCell(0));
                    System.out.println(cellData+",");
                }else{
                    break;
                }
                list.add(map);
            }
        }
        return list;
    }
    
    /** 根据读取文件的尾缀获取WB类型
     * @author guancong
     * @date 2022/4/1
     * @param filePath 文件类型 主要是尾缀
     * @return org.apache.poi.ss.usermodel.Workbook 
     * @version  1.0
     */
    public  Workbook getWorkBook(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    /**  获取指定数据里面的值
     * @author guancong
     * @date 2022/4/1
     * @param cell
     * @return java.lang.Object
     * @version  1.0
     */
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            cellValue = cell.getStringCellValue();
            //  cellValue = String.valueOf(numericCellValue);

        }else{
            cellValue = "";
        }
        return cellValue;
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
