package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author guacnong
 * 处理编码问题
 * @date 2022-01-24$
 */
@RestController
@RequestMapping("/code")
public class codeController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/newcode")
    public void  getData() {
        String  initsql = "select  code  from t_contract20220124   GROUP BY code ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(initsql);
        System.out.println("----list.size()-="+list.size());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            Map<String, Object> stringObjectMap = list.get(i);
            String  code = String.valueOf(stringObjectMap.get("code"));
            String  group = " select * from t_contract20220124   where code = '"+code + "'  order by audit_time asc  ";
            System.out.println("-----group="+group);
            List<Map<String, Object>> lis2 = jdbcTemplate.queryForList(group);
            if(lis2.size()>1){
                for (int j = 0; j < lis2.size(); j++) {
                    Map<String, Object> stringObjectMap2 = lis2.get(j);
                    String  code1 = String.valueOf(stringObjectMap2.get("code"));
                    code1 = code1.substring(0,code1.length()-4);
                    String  id = String.valueOf(stringObjectMap2.get("id"));
                    String number = "";
                    int k = j +1;
                    if (k <=9 ) {
                        number = "000" + k;
                    } else if (k < 100) {
                        number = "00" + k;
                    } else if (k< 1000) {
                        number = "0" + k;
                    } else {
                        number = ""+k;
                    }
                    code1 = code1 + number;
                    String str = "update t_contract20220124 set code ='"+code1+"' where id ="+id;
                    sb = sb.append(str+"\n");

                }
            }

        }
        try{
            testCreateFile1(sb.toString());
        }catch (Exception e){

        }
    }

    public static void   testCreateFile1(String token) throws IOException {
        String fileName = "src/main/resources/update.sql";
        Path path = Paths.get(fileName);
        System.out.println(path.toAbsolutePath());

        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)){
            writer.write(token);
        }
    }

}
