package com.example.demo.utils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
public class JxlExcelReadAndWrite {


    public void writeExcelByJxl(String path,String context,String[] title ){
        try{
            //创建Excel文件
            File file=new File(path);
            //创建文件
            file.createNewFile();
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            //创建sheet
            WritableSheet sheet=workbook.createSheet("sheet1",0);
            //添加数据
            // String[] title={"id","name","sex"};
            Label label=null;
            for (int i=0;i<title.length;i++){
                label=new Label(i,0,title[i]);
                sheet.addCell(label);
            }
            //追加数据
            for (int i=1;i<10;i++){
                label=new Label(0,i,"a"+1);
                sheet.addCell(label);
                label=new Label(1,i,"user"+1);
                sheet.addCell(label);
                label=new Label(2,i,"男"+1);
                sheet.addCell(label);
            }
            workbook.write();
            workbook.close();

        }catch (Exception e){
            System.out.println(e.toString());
        }

    }
}
