package com.example.demo.normal;

import com.alibaba.schedulerx.shade.com.google.gson.JsonArray;
import com.alibaba.schedulerx.shade.org.apache.http.client.config.RequestConfig;
import com.alibaba.schedulerx.shade.org.apache.http.impl.client.CloseableHttpClient;
import com.alibaba.schedulerx.shade.org.apache.http.impl.client.HttpClients;
import com.aliyun.log.thirdparty.org.apache.http.client.methods.HttpPost;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONArray;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.org.apache.commons.lang3.StringUtils;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.example.demo.http.AbstractApi;
import com.example.demo.http.HttpPostRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.*;
import java.util.*;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
public class test extends  AbstractApi{

    private static JdbcOperations jdbcTemplate;

    public static void main(String[] args) {

   /*  String str ="{\"createDeptId\":\"49215521\",\"createDeptName\":\"湖南江西分公司人力行政部\",\"supplementaryAgreementStatus\":\"01\",\"newPartyB\":\"湖南高新律师事务所\",\"updateTime\":\"\",\"sort\":1,\"version\":\"1.0\",\"newPartyA\":\"长沙居然之家家居建材营销有限公司\",\"signedTime\":\"1585584000000\",\"file\":\"[{\\\"newName\\\":\\\"service-contract/19a5d353-0104-492f-bcf2-0a2e16661806.pdf\\\",\\\"oldName\\\":\\\"发起的合同审查流程（+呼和浩特市居然之家商业管理有限公司与中国联合网络通信有+限公司呼和浩特市分公司_1628484653632.pdf\\\",\\\"fileType\\\":\\\"1\\\"},{\\\"newName\\\":\\\"service-contract/f4e27fd9-8dbd-447b-8a9c-54f4a4a33e07.pdf\\\",\\\"oldName\\\":\\\"联通通讯车租赁合同.pdf\\\",\\\"fileType\\\":\\\"1\\\"},{\\\"newName\\\":\\\"service-contract/53c10cf8-8582-4fbc-b6bb-43d219e85890.pdf\\\",\\\"oldName\\\":\\\"刘晓旭提交的请示报告（新零售）202107141440000432536(1).pdf\\\",\\\"fileType\\\":\\\"1\\\"}]\",\"createTime\":\"1605704674000\",\"createId\":\"10035020\",\"newMoney\":60000.00,\"contractId\":17839,\"startTime\":\"\",\"Id\":4410,\"endTime\":\"\",\"contractpproval\":0,\"status\":2}";
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject file = JSONArray.parseObject(jsonObject.get("file").toString());*/


       Integer a = -1;
        System.out.println(-1==a);

     /*  Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        System.out.println(startDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startdate = null;
        Date enddate = null;
        try {
            startdate = simpleDateFormat.parse("2022-01-17 00:00:00");
            enddate = simpleDateFormat.parse("2022-01-27 00:00:00");
        }catch (Exception e){
            e.printStackTrace();
        }

        startDate.setTime(startdate);
        endDate.setTime(enddate);
        System.out.println(startDate);
        System.out.println(endDate);
        startDate.add(Calendar.DAY_OF_MONTH, 1);
        List<Date> createDateList = new ArrayList<>();
        System.out.println(startDate);
        while (startDate.getTime().getTime() <= endDate.getTime().getTime()) {
            createDateList.add(startDate.getTime());
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println(createDateList.size());*/
      //  StampToTime();
       // String s = dateToStamp("");
        //System.out.println(s);
     /*   String s ="2021-12-30 15:50:10.0";
        s=s.substring(0,s.length()-2);
        System.out.println("s====="+s);
        StringBuilder sb = new StringBuilder();
        sb.append("啊啊啊啊十大大啊大声道啊a");
        System.out.println("sb====="+sb.toString());
        sb.setLength(0);
        System.out.println("sb====="+sb.toString());*/
       /// StampToTime();
        //String s = covnDate("2021-12-30 15:50:10.0");
       // String s1 = dateToStamp(s);
       // System.out.println("s====="+s);
       // System.out.println("s1====="+s1);
     //   uploadfile();
    }





    private static String uploadfile() {


        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        // 设置OSSClient使用的最大连接数，默认1024
        config.setMaxConnections(200);
        //  设置请求超时时间，默认50秒
        config.setSocketTimeout(10000);
        //  设置失败请求重试次数，默认3次

        config.setMaxErrorRetry(3);

        String endpoint="oss-cn-beijing.aliyuncs.com";  //oss地址
        String accessKeyId="LTAI5tDXvGTFoJmyEsPJ4EjB";   //osskeyid
        String accessKeySecret="eNwGgboC4uufXD1I5sMHmtTBpumkMt"; // oss secret

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, config);

        //上传文件
        File file = new File("C:\\Users\\guancong\\Desktop\\2021各系统交接补充信息\\crm\\Easyhome_ERP_CRM_Job列表V1.0.1.xlsx");//本机文件路径
        String  ceshifilename  = "测试文件名";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);

        String fileName = UUID.randomUUID().toString() + suffix;
        String targetFile = ceshifilename + fileName;
        PutObjectResult putObjectResult = ossClient.putObject("jurankefu", targetFile, fileInputStream);  //  bucketname; 目标文件名称; 文件流
        fileInputStream.close();
        ossClient.shutdown();
        if (null == putObjectResult.getResponse()) {
            return targetFile;
        }
        return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return endpoint;
    }

    /*
     * 时间戳转换为yyyy-MM-dd格式/
     */
    public static void StampToTime() {
        final long nowTime = System.currentTimeMillis();//获取系统当前时间

        long stamp = 1640850610000L;//设置你的时间
        final Date date = new Date(stamp);//新建一个时间对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//你要转换成的时间格式,大小写不要变
        final String yourtime = sdf.format(date);//转换你的时间
        final String nowtime = sdf.format(nowTime);
        System.out.println("当前系统时间："+nowtime+"\n"+stamp+" 转换后是："+yourtime);//打印出你转换后的时间
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    private static String covnDate(String dateTime) {
        DateFormat df2 = null;
        Date date1 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date = df.parse(dateTime);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = df2.format(date1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df2.format(date1);
    }

    }
