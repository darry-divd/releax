package com.example.demo.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSONObject;
import com.example.demo.config.MqConfig;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
@RestController
@RequestMapping("/tonbucontract")
public class testController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProducerBean producer;

    @Autowired
    private MqConfig mqConfig;

    @RequestMapping("/contract/a")
    public String  getData() {
        StringBuffer sb = new StringBuffer();
        String sql = "select t.id , t.`code`, \t\t\t\t\t\n" +
                "t.code_type, \t\t\t\t\n" +
                "t.code_dept_id, \t\t\t\n" +
                "t.`name`, \t\t\t\t\t\n" +
                "t.version, \t\t\t\t\n" +
                "t.money, \t\t\t\t\t\n" +
                "t.party_A, \t\t\t\t\n" +
                "t.party_B, \t\t\t\t\n" +
                "t.party_C, \t\t\t\t\n" +
                "t.party_D,\t\t\t\t\t\n" +
                "t.signed_time as signed_time, \t\t\t\n" +
                "t.start_stop_state, \t\t\n" +
                "t.start_time as start_time ,\t\t\t\t\n" +
                "t.end_time as end_time  ,\t\t\t\t\n" +
                "t.type_id,  \n" +
                "t.state,  \t\t\n" +
                "t.secret, \t\t\t\t\t\n" +
                "t.payment_ratio, \t\t\t\n" +
                "t.create_time as create_time  ,  \t\t\t\n" +
                "t.update_time as update_time , \t\t\t\n" +
                "t.audit_time as audit_time , \t\t\t\t\n" +
                "t.handle_deptId, \t\t\t\n" +
                "t.handle_deptName, \t\t\n" +
                "t.handle_userId, \t\t\t\n" +
                "t.handle_userName, \t\t\n" +
                "t.remark, \t\t\t\t\t\n" +
                "t.file, \t\t\t\t\t\n" +
                "t.is_delete,\t\t\t\t\n" +
                "t.fileing_palace, \t\t\t\n" +
                "t.number, \t\t\t\t\t\n" +
                "t.connect_unit_id, \t\t\n" +
                "t.connect_unit_name, \t\t\n" +
                "t.connect_user_id, \t\t\n" +
                "t.connect_user_name, \t\t\n" +
                "t.change_reason, \t\t\t\n" +
                "t.agreement_time as agreement_time ,\t\t\t\n" +
                "t.invalidation_reasons, \t\n" +
                "t.payment,\t\t\t\t\t\n" +
                "t.sort, \t\t\t\t\t\n" +
                "t.push_type,    \t\t\t\n" +
                "t.formInstId,\t\t\t\t\n" +
                "t.contract_flag, \t\t\t\n" +
                "t.`from` \t,\t\t\t\n" +
                "t.contract_pages, \t\t\t\n" +
                "t.destroy_reason, \t\t\t\n" +
                "t.destroy_time as destroy_time  , \t\t\t\n" +
                "t.destroy_user_id, \t\t\n" +
                "t.destroy_user_name, \t\t\n" +
                "t.del_reason, \t\t\t\t\n" +
                "t.del_user_id, \t\t\t\n" +
                "t.del_time as del_time  from  t_contract  t where id in (40377);  ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        String sql1 = "select * from t_contract_file where contract_id=";
        for (int i = 0; i <list.size(); i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            System.out.println(stringObjectMap.toString());
            Map<String, Object> contract =new HashMap<>();
            contract.put("id",stringObjectMap.get("id"));
            contract.put("code",stringObjectMap.get("code"));
            contract.put("codeType",stringObjectMap.get("code_type"));
            contract.put("codeDeptId",stringObjectMap.get("code_dept_id"));
            contract.put("name",stringObjectMap.get("name"));
            contract.put("version",stringObjectMap.get("version"));
            contract.put("money",stringObjectMap.get("money"));
            contract.put("partyA",stringObjectMap.get("party_A"));
            contract.put("partyB",stringObjectMap.get("party_B"));
            contract.put("partyC",stringObjectMap.get("party_C"));
            contract.put("partyD",stringObjectMap.get("party_D"));
            //contract.put("signedTime",stringObjectMap.get("signed_time"));
            contract.put("signedTime",transTime(String.valueOf(stringObjectMap.get("signed_time"))));
            contract.put("startStopState",stringObjectMap.get("start_stop_state"));
            // contract.put("startTime",stringObjectMap.get("start_time"));
            //contract.put("endTime",stringObjectMap.get("end_time"));
            contract.put("startTime",transTime(String.valueOf(stringObjectMap.get("start_time"))));
            contract.put("endTime",transTime(String.valueOf(stringObjectMap.get("end_time"))));
            contract.put("typeId",stringObjectMap.get("type_id"));
            contract.put("state",stringObjectMap.get("state"));
            contract.put("secret",stringObjectMap.get("secret"));
            contract.put("paymentRatio",stringObjectMap.get("payment_ratio"));
            // contract.put("createTime",stringObjectMap.get("create_time"));
            // contract.put("updateTime",stringObjectMap.get("update_time"));
            // contract.put("auditTime",stringObjectMap.get("audit_time"));
            contract.put("createTime",transTime(String.valueOf(stringObjectMap.get("create_time"))));
            contract.put("updateTime",transTime(String.valueOf(stringObjectMap.get("update_time"))));
            contract.put("auditTime",transTime(String.valueOf(stringObjectMap.get("audit_time"))));


            contract.put("handleDeptid",stringObjectMap.get("handle_deptId"));
            contract.put("handleDeptname",stringObjectMap.get("handle_deptName"));
            contract.put("handleUserid",stringObjectMap.get("handle_userId"));
            contract.put("handleUsername",stringObjectMap.get("handle_userName"));
            contract.put("remark",stringObjectMap.get("remark"));
            String  sql2= sql1 + "'"+ String.valueOf(stringObjectMap.get("id")) +"' and type = 0 " ;
            String  sql3= sql1 + "'"+ String.valueOf(stringObjectMap.get("id")) +"' and type = 1 " ;
            List<Map<String, Object>> filelist = jdbcTemplate.queryForList(sql2);
            List<Map<String, Object>> filelist2 = jdbcTemplate.queryForList(sql3);
            if(!filelist.isEmpty()){
                List<Map<String, Object>> fileNewNameList = new ArrayList<>();
                for (Map<String, Object> map: filelist ) {
                   Map<String, Object> fileMap = new HashMap<>();
                    System.out.println(map.toString());
                    fileMap.put("oldName",map.get("old_name"));
                    //这个是上传合同数据至oss之后给的文件地址数据
                    fileMap.put("newName",map.get("new_name"));
                    //   0 合同文件  1 协议文件
                    fileMap.put("fileType","0");
                    fileNewNameList.add(fileMap);
                }
                if(!filelist2.isEmpty()){
                    for (Map<String, Object> map: filelist2 ) {
                        Map<String, Object> fileMap = new HashMap<>();
                        System.out.println(map.toString());
                        fileMap.put("oldName",map.get("old_name"));
                        //这个是上传合同数据至oss之后给的文件地址数据
                        fileMap.put("newName",map.get("new_name"));
                        //   0 合同文件  1 协议文件
                        fileMap.put("fileType","1");
                        fileNewNameList.add(fileMap);
                    }
                }

                contract.put("file", JSONObject.toJSONString(fileNewNameList));
            }

            contract.put("isDelete",stringObjectMap.get("is_delete"));
            contract.put("fileingPalace",stringObjectMap.get("fileing_palace"));
            contract.put("connectUnitId",stringObjectMap.get("connect_unit_id"));
            contract.put("connectUnitName",stringObjectMap.get("connect_unit_name"));
            contract.put("connectUserId",stringObjectMap.get("connect_user_id"));
            contract.put("connectUserName",stringObjectMap.get("connect_user_name"));
            contract.put("changeReason",stringObjectMap.get("change_reason"));

            contract.put("agreementTime",transTime(String.valueOf(stringObjectMap.get("agreement_time"))));
            //  contract.put("agreementTime",stringObjectMap.get("agreement_time"));
            contract.put("invalidationReasons",stringObjectMap.get("invalidation_reasons"));
            contract.put("payment",stringObjectMap.get("payment"));
            contract.put("sort",stringObjectMap.get("sort"));
            contract.put("pushType",stringObjectMap.get("push_type"));
            contract.put("formInstId",stringObjectMap.get("formInstId"));
            contract.put("contractFlag",stringObjectMap.get("contract_flag"));
            contract.put("from",stringObjectMap.get("from"));
            contract.put("contractPages",stringObjectMap.get("contract_pages"));
            contract.put("destroyReason",stringObjectMap.get("destroy_reason"));
            // contract.put("destroyTime",stringObjectMap.get("destroy_time"));
            contract.put("destroyTime",transTime(String.valueOf(stringObjectMap.get("destroy_time"))));
            contract.put("destroyUserId",stringObjectMap.get("destroy_user_id"));
            contract.put("destroyUserName",stringObjectMap.get("destroy_user_name"));
            contract.put("delReason",stringObjectMap.get("del_reason"));
            contract.put("delUserId",stringObjectMap.get("del_user_id"));

            String s = JSON.toJSONString(contract);
            JSONObject jsonObject = JSON.parseObject(s);
            jsonObject.put("contractStatus","02");
            System.out.println(jsonObject.toJSONString());
            sb = sb.append(jsonObject.toJSONString()+"\n");
        }
      /*  try {
            testCreateFile(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return  "2222";

    }
    @RequestMapping("/contract111")
    public List<Map<String, Object>> getDbType1(){
        String sql = "select t.id , t.`code`, \t\t\t\t\t\n" +
                "t.code_type, \t\t\t\t\n" +
                "t.code_dept_id, \t\t\t\n" +
                "t.`name`, \t\t\t\t\t\n" +
                "t.version, \t\t\t\t\n" +
                "t.money, \t\t\t\t\t\n" +
                "t.party_A, \t\t\t\t\n" +
                "t.party_B, \t\t\t\t\n" +
                "t.party_C, \t\t\t\t\n" +
                "t.party_D,\t\t\t\t\t\n" +
                "t.signed_time, \t\t\t\n" +
                "t.start_stop_state, \t\t\n" +
                "t.start_time,\t\t\t\t\n" +
                "t.end_time,\t\t\t\t\n" +
                "t.type_id,  \n" +
                "t.state,  \t\t\n" +
                "t.secret, \t\t\t\t\t\n" +
                "t.payment_ratio, \t\t\t\n" +
                "t.create_time,  \t\t\t\n" +
                "t.update_time, \t\t\t\n" +
                "t.audit_time, \t\t\t\t\n" +
                "t.handle_deptId, \t\t\t\n" +
                "t.handle_deptName, \t\t\n" +
                "t.handle_userId, \t\t\t\n" +
                "t.handle_userName, \t\t\n" +
                "t.remark, \t\t\t\t\t\n" +
                "t.file, \t\t\t\t\t\n" +
                "t.is_delete,\t\t\t\t\n" +
                "t.fileing_palace, \t\t\t\n" +
                "t.number, \t\t\t\t\t\n" +
                "t.connect_unit_id, \t\t\n" +
                "t.connect_unit_name, \t\t\n" +
                "t.connect_user_id, \t\t\n" +
                "t.connect_user_name, \t\t\n" +
                "t.change_reason, \t\t\t\n" +
                "t.agreement_time,\t\t\t\n" +
                "t.invalidation_reasons, \t\n" +
                "t.payment,\t\t\t\t\t\n" +
                "t.sort, \t\t\t\t\t\n" +
                "t.push_type,    \t\t\t\n" +
                "t.formInstId,\t\t\t\t\n" +
                "t.contract_flag, \t\t\t\n" +
                "t.`from` \t,\t\t\t\n" +
                "t.contract_pages, \t\t\t\n" +
                "t.destroy_reason, \t\t\t\n" +
                "t.destroy_time, \t\t\t\n" +
                "t.destroy_user_id, \t\t\n" +
                "t.destroy_user_name, \t\t\n" +
                "t.del_reason, \t\t\t\t\n" +
                "t.del_user_id, \t\t\t\n" +
                "t.del_time  from  t_contract20220125  t where t.id  in (select  contract_id from t_contract_file20220125  where type =1   and contract_id not in (select  contract_id from t_contract_file20220125  where type =0 )) ;";

        return null;
    }

    @RequestMapping("/contract")
    public List<Map<String, Object>> getDbType(){
        int count =0;
        String sql = "select t.id , t.`code`, \t\t\t\t\t\n" +
                "t.code_type, \t\t\t\t\n" +
                "t.code_dept_id, \t\t\t\n" +
                "t.`name`, \t\t\t\t\t\n" +
                "t.version, \t\t\t\t\n" +
                "t.money, \t\t\t\t\t\n" +
                "t.party_A, \t\t\t\t\n" +
                "t.party_B, \t\t\t\t\n" +
                "t.party_C, \t\t\t\t\n" +
                "t.party_D,\t\t\t\t\t\n" +
                "t.signed_time, \t\t\t\n" +
                "t.start_stop_state, \t\t\n" +
                "t.start_time,\t\t\t\t\n" +
                "t.end_time,\t\t\t\t\n" +
                "t.type_id,  \n" +
                "t.state,  \t\t\n" +
                "t.secret, \t\t\t\t\t\n" +
                "t.payment_ratio, \t\t\t\n" +
                "t.create_time,  \t\t\t\n" +
                "t.update_time, \t\t\t\n" +
                "t.audit_time, \t\t\t\t\n" +
                "t.handle_deptId, \t\t\t\n" +
                "t.handle_deptName, \t\t\n" +
                "t.handle_userId, \t\t\t\n" +
                "t.handle_userName, \t\t\n" +
                "t.remark, \t\t\t\t\t\n" +
                "t.file, \t\t\t\t\t\n" +
                "t.is_delete,\t\t\t\t\n" +
                "t.fileing_palace, \t\t\t\n" +
                "t.number, \t\t\t\t\t\n" +
                "t.connect_unit_id, \t\t\n" +
                "t.connect_unit_name, \t\t\n" +
                "t.connect_user_id, \t\t\n" +
                "t.connect_user_name, \t\t\n" +
                "t.change_reason, \t\t\t\n" +
                "t.agreement_time,\t\t\t\n" +
                "t.invalidation_reasons, \t\n" +
                "t.payment,\t\t\t\t\t\n" +
                "t.sort, \t\t\t\t\t\n" +
                "t.push_type,    \t\t\t\n" +
                "t.formInstId,\t\t\t\t\n" +
                "t.contract_flag, \t\t\t\n" +
                "t.`from` \t,\t\t\t\n" +
                "t.contract_pages, \t\t\t\n" +
                "t.destroy_reason, \t\t\t\n" +
                "t.destroy_time, \t\t\t\n" +
                "t.destroy_user_id, \t\t\n" +
                "t.destroy_user_name, \t\t\n" +
                "t.del_reason, \t\t\t\t\n" +
                "t.del_user_id, \t\t\t\n" +
                "t.del_time  from  t_contract20220125  t where t.type_id IN ( '726f546f-147c-4f5f-9063-9092fb18ca94', '66cbbb69-9965-49ae-85c1-66f38d491e74', 'ad5288a3-8737-47ce-a0f6-d71803cc2362', '01d2b814-8f7e-41b2-962b-e950bd178e88', '51ac16c8-7ec3-446a-80b8-135a86c63785' )" +
                "   and   t.id  in (select  contract_id from t_contract_file20220125  where type =1   and contract_id not in (select  contract_id from t_contract_file20220125  where type =0 )) ;  ";
        String sql1 = "select * from t_contract_file20220125 where contract_id=";
        StringBuffer sb = new StringBuffer();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> contract =new HashMap<>();
        for (int i = 0; i <list.size(); i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String id = String.valueOf(stringObjectMap.get("id"));
          String  sql2= sql1 + "'"+ id +"' and type = 0 " ;
            String  sql3= sql1 + "'"+ id +"' and type = 1" ;
            System.out.println(sql2 );

            contract.put("id",stringObjectMap.get("id"));
            contract.put("code",stringObjectMap.get("code"));
            contract.put("codeType",stringObjectMap.get("code_type"));
            contract.put("codeDeptId",stringObjectMap.get("code_dept_id"));
            contract.put("name",stringObjectMap.get("name"));
            contract.put("version",stringObjectMap.get("version"));
            contract.put("money",stringObjectMap.get("money"));
            contract.put("partyA",stringObjectMap.get("party_A"));
            contract.put("partyB",stringObjectMap.get("party_B"));
            contract.put("partyC",stringObjectMap.get("party_C"));
            contract.put("partyD",stringObjectMap.get("party_D"));
            //contract.put("signedTime",stringObjectMap.get("signed_time"));
            contract.put("signedTime",transTime(String.valueOf(stringObjectMap.get("signed_time"))));
            contract.put("startStopState",stringObjectMap.get("start_stop_state"));
           // contract.put("startTime",stringObjectMap.get("start_time"));
            //contract.put("endTime",stringObjectMap.get("end_time"));
            String start_time = transTime(String.valueOf(stringObjectMap.get("start_time")));
            String end_time = transTime(String.valueOf(stringObjectMap.get("end_time")));
           /* if("".equals(start_time) || "".equals(end_time) ){
                System.out.println("--时间为空数据不同步---合同id="+stringObjectMap.get("id"));
                continue;
            }*/
            contract.put("startTime",transTime(String.valueOf(stringObjectMap.get("start_time"))));
            contract.put("endTime",transTime(String.valueOf(stringObjectMap.get("end_time"))));
            contract.put("typeId",stringObjectMap.get("type_id"));
            contract.put("state",stringObjectMap.get("state"));
            contract.put("secret",stringObjectMap.get("secret"));
            contract.put("paymentRatio",stringObjectMap.get("payment_ratio"));
           // contract.put("createTime",stringObjectMap.get("create_time"));
           // contract.put("updateTime",stringObjectMap.get("update_time"));
           // contract.put("auditTime",stringObjectMap.get("audit_time"));
            contract.put("createTime",transTime(String.valueOf(stringObjectMap.get("create_time"))));
            contract.put("updateTime",transTime(String.valueOf(stringObjectMap.get("update_time"))));
            contract.put("auditTime",transTime(String.valueOf(stringObjectMap.get("audit_time"))));


            contract.put("handleDeptid",stringObjectMap.get("handle_deptId"));
            contract.put("handleDeptname",stringObjectMap.get("handle_deptName"));
            contract.put("handleUserid",stringObjectMap.get("handle_userId"));
            contract.put("handleUsername",stringObjectMap.get("handle_userName"));
            contract.put("remark",stringObjectMap.get("remark"));
            List<Map<String, Object>> filelist = jdbcTemplate.queryForList(sql2);
            List<Map<String, Object>> filelist2 = jdbcTemplate.queryForList(sql3);
            List<Map<String, Object>> fileNewNameList = new ArrayList<>();
            if(!filelist.isEmpty() ){
                for (Map<String, Object> map: filelist ) {
                    Map<String, Object> fileMap =new HashMap<>();
                    System.out.println(map.toString());
                    fileMap.put("oldName",map.get("old_name"));
                    //这个是上传合同数据至oss之后给的文件地址数据
                    fileMap.put("newName",map.get("new_name"));
                    //   0 合同文件  1 协议文件
                    fileMap.put("fileType","0");
                    fileNewNameList.add(fileMap);
                }
                if(!filelist2.isEmpty()){
                    for (Map<String, Object> map: filelist2 ) {
                        Map<String, Object> fileMap =new HashMap<>();
                        System.out.println(map.toString());
                        fileMap.put("oldName",map.get("old_name"));
                        //这个是上传合同数据至oss之后给的文件地址数据
                        fileMap.put("newName",map.get("new_name"));
                        //   0 合同文件  1 协议文件
                        fileMap.put("fileType","1");
                        fileNewNameList.add(fileMap);
                    }
                }
                contract.put("file", JSONObject.toJSONString(fileNewNameList));
            }else{
                if(!filelist2.isEmpty()){
                    for (Map<String, Object> map: filelist2 ) {
                        Map<String, Object> fileMap =new HashMap<>();
                        System.out.println(map.toString());
                        fileMap.put("oldName",map.get("old_name"));
                        //这个是上传合同数据至oss之后给的文件地址数据
                        fileMap.put("newName",map.get("new_name"));
                        //   0 合同文件  1 协议文件
                        fileMap.put("fileType","1");
                        fileNewNameList.add(fileMap);
                    }
                }else{

                    System.out.println("--附件为空数据不同步---合同id="+stringObjectMap.get("id"));
                    count++;
                    continue;
                }


            }

            contract.put("isDelete",stringObjectMap.get("is_delete"));
            contract.put("fileingPalace",stringObjectMap.get("fileing_palace"));
            contract.put("connectUnitId",stringObjectMap.get("connect_unit_id"));
            contract.put("connectUnitName",stringObjectMap.get("connect_unit_name"));
            contract.put("connectUserId",stringObjectMap.get("connect_user_id"));
            contract.put("connectUserName",stringObjectMap.get("connect_user_name"));
            contract.put("changeReason",stringObjectMap.get("change_reason"));

            contract.put("agreementTime",transTime(String.valueOf(stringObjectMap.get("agreement_time"))));
          //  contract.put("agreementTime",stringObjectMap.get("agreement_time"));
            contract.put("invalidationReasons",stringObjectMap.get("invalidation_reasons"));
            contract.put("payment",stringObjectMap.get("payment"));
            contract.put("sort",stringObjectMap.get("sort"));
            contract.put("pushType",stringObjectMap.get("push_type"));
            contract.put("formInstId",stringObjectMap.get("formInstId"));
            contract.put("contractFlag",stringObjectMap.get("contract_flag"));
            contract.put("from",stringObjectMap.get("from"));
            contract.put("contractPages",stringObjectMap.get("contract_pages"));
            contract.put("destroyReason",stringObjectMap.get("destroy_reason"));
           // contract.put("destroyTime",stringObjectMap.get("destroy_time"));
            contract.put("destroyTime",transTime(String.valueOf(stringObjectMap.get("destroy_time"))));
            contract.put("destroyUserId",stringObjectMap.get("destroy_user_id"));
            contract.put("destroyUserName",stringObjectMap.get("destroy_user_name"));
            contract.put("delReason",stringObjectMap.get("del_reason"));
            contract.put("delUserId",stringObjectMap.get("del_user_id"));

            String s = JSON.toJSONString(contract);
            JSONObject jsonObject = JSON.parseObject(s);
            jsonObject.put("contractStatus","01");
            System.out.println(jsonObject.toJSONString());
            sb = sb.append(jsonObject.toJSONString()+"\n");
//            if(i == 0){
//                sb = sb.append(jsonObject.toJSONString());
//            }else{
//                sb =sb.append("@" +jsonObject.toJSONString() );
//            }
        }
        try {
            testCreateFile(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("没有附件的合同数量为:"+count);
        return list;
    }


    @RequestMapping("/contractagremnet")
    public List<Map<String, Object>> getcontractagremnet(){
        int cou=0;
        StringBuffer sb = new StringBuffer();
         /* String sql = "select t.id , t.contract_id, \t\t\t\t\t\n" +
                "t.version, \t\t\t\t\n" +
                "t.new_party_A, \t\t\t\n" +
                "t.new_party_B, \t\t\t\t\t\n" +
                "t.new_party_C, \t\t\t\t\n" +
                "t.new_party_D, \t\t\t\t\t\n" +
                "t.new_money, \t\t\t\t\n" +
                "t.file, \t\t\t\t\n" +
                "t.remark,\n" +
                "t.create_time as create_time,\t\t\t\n" +
                "t.`status`,\t\t\t\t\t \t\t\t\n" +
                "t.update_time as update_time,\t\t\t\n" +
                "t.create_name, \n" +
                "t.create_id, \n" +
                "t.type, \n" +
                "t.create_dept_id, \n" +
                "t.create_dept_name, \n" +
                "t.file_remarks, \n" +
                "t.start_time as start_time,\t\n" +
                "t.end_time as end_time,\t\n" +
                "t.signed_time as signed_time,\t\n" +
                "t.sort, \n" +
                "t.start_stop_state, \n" +
                "t.connect_unit_id, \n" +
                "t.connect_unit_name, \n" +
                "t.connect_user_id, \t\n" +
                "t.connect_user_name, \t\n" +
                "t.audit_time as audit_time,\t\n" +
                "t.invalidation_reasons, \t\n" +
                "t.del_reason, \t\n" +
                "t.contract_approval, \t\n" +
                "t.del_user_id, \t\n" +
                "t.del_time as del_time  from  t_contract_agreement20220125  t;  ";*/
      String sql = "select t.id , t.contract_id  from  t_contract_agreement t where  t.contract_id in (38796,25169); ";

        String sql1 = "select * from t_contract_file20220125 where contract_id=";
        String sql3 = "select code from t_contract20220125 where  id=";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> contract =new HashMap<>();
        for (int i = 0; i <list.size(); i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String id = String.valueOf(stringObjectMap.get("contract_id"));
            String sql2 = sql1 + "'" + id + "' and type = 1";
          //  String sql4 = sql3  + id ;
          //  List<Map<String, Object>> list2 = jdbcTemplate.queryForList(sql4);
            contract.put("Id",stringObjectMap.get("id"));
            contract.put("contractId",stringObjectMap.get("contract_id"));
            contract.put("code",stringObjectMap.get("code"));
          /*  contract.put("version",stringObjectMap.get("version"));
            contract.put("newPartyA",stringObjectMap.get("new_party_A"));
            contract.put("newPartyB",stringObjectMap.get("new_party_B"));
            contract.put("newPartyC",stringObjectMap.get("new_party_C"));
            contract.put("newPartyD",stringObjectMap.get("new_party_D"));
            contract.put("newMoney",stringObjectMap.get("new_money"));*/
           /* contract.put("file",stringObjectMap.get("file"));
            contract.put("remark",stringObjectMap.get("remark"));

           // contract.put("createTime",stringObjectMap.get("create_time"));
            contract.put("createTime",transTime(String.valueOf(stringObjectMap.get("create_time"))));

            contract.put("status",stringObjectMap.get("status"));
           // contract.put("updateTime",stringObjectMap.get("update_time"));
           // contract.put("createName",stringObjectMap.get("create_name"));
            contract.put("updateTime",transTime(String.valueOf(stringObjectMap.get("updateTime"))));
            contract.put("createId",stringObjectMap.get("create_id"));
            contract.put("type",stringObjectMap.get("type"));
            contract.put("createDeptId",stringObjectMap.get("create_dept_id"));
            contract.put("createDeptName",stringObjectMap.get("create_dept_name"));
            contract.put("fileRemarks",stringObjectMap.get("file_remarks"));
           // contract.put("startTime",stringObjectMap.get("start_time"));
           // contract.put("endTime",stringObjectMap.get("end_time"));
           // contract.put("signedTime",stringObjectMap.get("signed_time"));
            String start_time = transTime(String.valueOf(stringObjectMap.get("start_time")));
            String end_time = transTime(String.valueOf(stringObjectMap.get("end_time")));
*/
            /*if("".equals(start_time) || "".equals(end_time) ){
                System.out.println("--时间为空数据不同步---合同id="+stringObjectMap.get("id"));
                continue;
            }*/
           /* contract.put("startTime",transTime(String.valueOf(stringObjectMap.get("start_time"))));
            contract.put("endTime",transTime(String.valueOf(stringObjectMap.get("end_time"))));*/

           /* contract.put("signedTime",transTime(String.valueOf(stringObjectMap.get("signed_time"))));
            contract.put("sort",stringObjectMap.get("sort"));
            contract.put("startStopState",stringObjectMap.get("start_stop_state"));
            contract.put("connectUnitId",stringObjectMap.get("connect_unit_id"));
            contract.put("connectUnitName",stringObjectMap.get("connect_unit_name"));
            contract.put("connectUserId",stringObjectMap.get("connect_user_id"));
            contract.put("connectUserName",stringObjectMap.get("connect_user_name"));
            contract.put("auditTime",stringObjectMap.get("audit_time"));
            contract.put("invalidationReasons",stringObjectMap.get("invalidation_reasons"));
            contract.put("delReason",stringObjectMap.get("del_reason"));
            contract.put("contractpproval",stringObjectMap.get("contract_approval"));
            contract.put("delUserId",stringObjectMap.get("del_user_id"));
            contract.put("delTime",stringObjectMap.get("del_time"));*/
            List<Map<String, Object>> filelist = jdbcTemplate.queryForList(sql2);
            if(!filelist.isEmpty()){
                List<Map<String, Object>> fileNewNameList = new ArrayList<>();
                for (Map<String, Object> map: filelist ) {
                    Map<String, Object> fileMap =new HashMap<>();
                    fileMap.put("oldName",map.get("old_name"));
                    //这个是上传合同数据至oss之后给的文件地址数据
                    fileMap.put("newName",map.get("new_name"));
                    //   0 合同文件  1 协议文件
                    fileMap.put("fileType","1");
                    fileNewNameList.add(fileMap);
                }
                contract.put("file", JSONObject.toJSONString(fileNewNameList));
            }/*else {
                System.out.println("--附件为空数据不同步---合同id="+stringObjectMap.get("id"));
                cou++;
                continue;
            }*/

            String s = JSON.toJSONString(contract);
            JSONObject jsonObject = JSON.parseObject(s);
            jsonObject.put("supplementaryAgreementStatus","02");
            System.out.println(jsonObject.toJSONString());
            sb = sb.append(jsonObject.toJSONString()+"\n");


        }
       /* try {
            testCreateFile1(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("没有附件的协议数量为"+cou);
        return list;
    }





    @RequestMapping("/user/{id}")
    public Map<String,Object> getUser(@PathVariable String id){
        Map<String,Object> map = null;

        List<Map<String, Object>> list = getDbType();

        for (Map<String, Object> dbmap : list) {

            Set<String> set = dbmap.keySet();

            for (String key : set) {
                if(key.equals("id")){
                    if(dbmap.get(key).equals(id)){
                        map = dbmap;
                    }
                }
            }
        }

        if(map==null){
            map = list.get(0);
        }

        return map;
    }
    @RequestMapping("/test")
    public  void test1(){
        System.out.println("shezhishuju ");
        Message msg= new Message();
        msg.setTopic(mqConfig.getTopic());
        msg.setTag("CREATE_CONTRACT");
        Map<String,String> map = new HashMap<>();
        map.put("name","ceshi");
        map.put("code","1001");
        String s = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(s);
        jsonObject.put("contractStatus","01");
        msg.setKey("1111");
        msg.setBody(jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
        System.out.println(msg.toString());
        SendResult sendResult = producer.send(msg);
        System.out.println(sendResult);
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
    public static void   testCreateFile(String token) throws IOException {
        String fileName = "src/main/resources/token3.txt";
        Path path = Paths.get(fileName);
        System.out.println(path.toAbsolutePath());

        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)){
            writer.write(token);
        }
    }

    public static void   testCreateFile1(String token) throws IOException {
        String fileName = "src/main/resources/token2.txt";
        Path path = Paths.get(fileName);
        System.out.println(path.toAbsolutePath());

        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)){
            writer.write(token);
        }
    }



    public static String transTime(String s){
        if(s==null || s.length()<=0 || "null".equals(s) || "".equals(s)){
            return "";
        }else{
            if(s.contains("T")){
                String S2 = covnDate(s);
                String S3 = dateToStamp(S2);
                System.out.println("----T--"+s+"---转换后为==="+S3);
                return S3;
            }else{
                String S2=s.substring(0,s.length()-2);
                String S3 = dateToStamp(S2);
                return S3;
            }
        }
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
        System.out.println("---covnDate--dateTime="+dateTime);
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
