package com.example.demo.utils;

import java.io.*;

/**
 *  读写txt 工具类
 *
 * @author guacnong
 * @date $
 */
public class txtUtils {

    /**使用FileOutputStream来写入txt文件
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath,String content){
        try {
            File file = new File(txtPath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if(file.exists()){
                //判断文件是否存在，如果不存在就新建一个txt
                file.createNewFile();
            }
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  String  readTxt(String filePath){
        try {
            File file=new File(filePath);
            StringBuffer sb = new StringBuffer();
            //判断文件是否存在
            if(file.isFile() && file.exists()){
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    sb.append(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return null;
        }

    }

}
