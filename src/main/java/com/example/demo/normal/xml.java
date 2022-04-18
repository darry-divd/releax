package com.example.demo.normal;


import java.io.*;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
public class xml {


   /* public static void main(String[] args) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = "C:\\Users\\guancong\\Desktop\\2222.xlsx";
        String columns[] = {"name","age","score"};
        wb = readExcel(filePath);
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


    }
    //读取excel
    public static Workbook readExcel(String filePath){
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
*/




    public static void main(String[] args) throws Exception {
        //1.创建SAXBuilder对象
        SAXBuilder saxBuilder = new SAXBuilder();
        //2.创建输入流
        InputStream is = new FileInputStream(new File("src/main/resources/activemq2.xml"));

        String rpathfinal = "src/main/resources/"+ "11112.xlsx";//路径
        SXSSFWorkbook wb = new SXSSFWorkbook(1024); // 这里1024是在内存中的数量，如果大于此数量时，会写到硬盘，以避免在内存导致内存溢出
        Sheet sh = wb.createSheet();

        //3.将输入流加载到build中
        Document document = saxBuilder.build(is);
        //4.获取根节点
        Element rootElement = document.getRootElement();
        //5.获取子节点
        List<Element> children = rootElement.getChildren();
        int i = 0;
        for (Element child : children) {
            System.out.println("通过name获取属性值:"+child.getAttribute("name"));
            List<Attribute> attributes = child.getAttributes();
            //打印属性
            for (Attribute attr : attributes) {
                System.out.println(attr.getName()+":"+attr.getValue());
            }
            List<Element> childrenList = child.getChildren();
            System.out.println("======获取子节点-start======");
            for (Element o : childrenList) {
                sh.setColumnWidth(i,4500);
                Row row = sh.createRow(i);
                if (i==0) {
                    row.createCell(0).setCellValue("topic");
                    row.createCell(1).setCellValue("size");
                    row.createCell(2).setCellValue("consumerCount");
                    row.createCell(3).setCellValue("enqueueCount");
                    row.createCell(4).setCellValue("dequeueCount");
                }else{
                    row.createCell(0).setCellValue(String.valueOf(child.getAttribute("name").getValue()));
                    row.createCell(1).setCellValue(String.valueOf(o.getAttribute("size").getValue()));
                    row.createCell(2).setCellValue(String.valueOf(o.getAttribute("consumerCount").getValue()));
                    row.createCell(3).setCellValue(String.valueOf(o.getAttribute("enqueueCount").getValue()));
                    row.createCell(4).setCellValue(String.valueOf(o.getAttribute("dequeueCount").getValue()));
                }
                System.out.println("节点名:"+o.getName()+"--size-"+"节点值:"+o.getAttribute("size"));
                System.out.println("节点名:"+o.getName()+"--consumerCount-"+"节点值:"+o.getAttribute("consumerCount").getValue());
                System.out.println("节点名:"+o.getName()+"-enqueueCount--"+"节点值:"+o.getAttribute("enqueueCount"));
                System.out.println("节点名:"+o.getName()+"-dequeueCount--"+"节点值:"+o.getAttribute("dequeueCount"));

            }
            System.out.println("======获取子节点-end======");
            i++;
        }
        FileOutputStream output=new FileOutputStream(rpathfinal);
        wb.write(output);
        output.close();
    }
}
