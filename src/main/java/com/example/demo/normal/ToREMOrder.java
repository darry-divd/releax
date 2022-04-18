package com.example.demo.normal;

import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * 功能描述 读取同步REM订单 用来生成 脚本数据
 *
 * @author guacnong
 * @date $
 */
public class ToREMOrder {

    public static void main(String[] args) {
        String filePath = "src/main/resources/111111.txt";
        String type ="SYNCSECKILLORDERSUBMIT";
        readTxtFile(filePath,type);
        writeTxt("src/main/resources/22222.txt","sdsadadsadadsadsadaafasfasasasdsadsadsadasdad");
    }


    public static void readTxtFile(String filePath,String type){
        String sql1 = "INSERT INTO `public_center`.`interface_fail_record`(`id`, `interface_type`, `request_json`, `response_json`, `create_time`, `retry_count`) VALUES (";
       StringBuffer sb = new StringBuffer();
        JSONObject js = new JSONObject();
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String str = "";
                    Long a = 11112222333300001L;
                    String[] split = lineTxt.split(",");
                    str =sql1 + "'"+ a +"','"+type+"','{";
                    str =str +"\"orderNo\":"+"\""+split[0]+"\",";
                    str =str +"\"flowId\":"+"\""+split[1]+"\",";
                    str =str +"\"storeId\":"+"\""+split[2]+"\",";
                    str =str +"\"shopId\":"+"\""+split[3]+"\",";
                    str =str +"\"payPrice\":"+split[4]+",";
                    str =str +"\"payTime\":"+"\""+split[5]+"\",";
                    str =str +"\"creator\":"+"\""+split[6]+"\",";
                    str =str +"\"activityChildBasicId\":"+"\""+split[7]+"\",";
                    str =str +"\"productId\":"+"\""+split[8]+"\",";
                    str =str +"\"activityName\":"+"\""+split[9]+"\",";
                    str =str +"\"receiverContact\":"+"\""+split[10]+"\",";
                    str =str +"\"receiverMobile\":"+"\""+split[11]+"\",";
                    str =str +"\"receiverAddress\":"+"\""+"\",";
                    str =str +"\"ture\":"+"\""+"\"";
                    str =str+"}', '', '2022-03-31 17:00:20', 2);";
                  // String sql2 = sql1 + "'"+ a +"','"+"SYNCSECKILLORDERSUBMIT"+"',"+s+"', '', '2022-03-31 17:00:20', 2);";
                   a++;
                    System.out.println(str);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }


            /**使用FileOutputStream来写入txt文件
             * @param txtPath txt文件路径
             * @param content 需要写入的文本
             */
            public static void writeTxt(String txtPath,String content){
                FileOutputStream fileOutputStream = null;
                File file = new File(txtPath);
                try {
                    if(file.exists()){
                        //判断文件是否存在，如果不存在就新建一个txt
                        file.createNewFile();
                    }
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(content.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


    public static void readFile(String filePath,String type){
        try {
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){

                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

    }
    }
