package com.example.demo.normal;

import com.aliyun.openservices.shade.io.netty.util.internal.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 功能描述 daochu excel
 *
 *
 * @author guacnong
 * @date $
 */
public class excel {


    public  void daochuexcel() throws Exception {
        String rpathfinal = "src/main/resources/"+ "mq.excel";//路径

        SXSSFWorkbook wb = new SXSSFWorkbook(1024); // 这里1024是在内存中的数量，如果大于此数量时，会写到硬盘，以避免在内存导致内存溢出
        Sheet sh = wb.createSheet();
/*
        for (int i = 0; i < List.size()+1; i++) {
            sh.setColumnWidth(i,4500);
            Row row = sh.createRow(i);
            if (i==0) {
                row.createCell(0).setCellValue("xxxx");
                row.createCell(1).setCellValue("aaaa");
                row.createCell(2).setCellValue("bbbb");
                row.createCell(3).setCellValue("cccc");
            }else{
                row.createCell(0).setCellValue(StringUtil.isNullOrEmpty(List.get(i-1).get("belong")));
                row.createCell(1).setCellValue(StringUtil.isNullOrEmpty(List.get(i-1).get("dqbj")));
                row.createCell(2).setCellValue(StringUtil.isNullOrEmpty(List.get(i-1).get("datasource")));
                row.createCell(3).setCellValue(StringUtil.isNullOrEmpty(List.get(i-1).get("tablename")));
            }
        }
        FileOutputStream output=new FileOutputStream(rpathfinal);
        wb.write(output);
        output.close();*/
    }
}
