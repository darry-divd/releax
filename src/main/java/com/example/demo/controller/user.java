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
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
@RestController
@RequestMapping("/user")
public class user {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/info")
    public String  getData() {
        String sql = "select user_id, type from user_info where type ='112' ;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        StringBuffer sb = new StringBuffer();
        String sql0= "INSERT INTO `t_relation`(`id`, `app_alias`, `alias_flag`, `group_id`, `user_id`, `is_master`, `role_id`, `status`, `type`, `create_time`, `create_by`, `update_time`, `update_by`) ";
        int id = 67527;
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String user_id = String.valueOf(stringObjectMap.get("user_id"));
            id = id +1;
            // qtrsxy  pxxy  jc（zz）xy  hkdaxy   lhfwxy   fpxy   sxxy   sfbgxy  ld（w）ht
            String sql1 = "  VALUES ("+id+", 'qtrsxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql2 = "  VALUES ("+id+", 'pxxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql3 = "  VALUES ("+id+", 'jc（zz）xy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql4 = "  VALUES ("+id+", 'hkdaxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql5 = "  VALUES ("+id+", 'lhfwxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql6 = "  VALUES ("+id+", 'fpxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql7 = "  VALUES ("+id+", 'sxxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql8 = "  VALUES ("+id+", 'sfbgxy', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            id = id +1;
            String sql9 = "  VALUES ("+id+", 'ld（w）ht', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-29 15:28:28', '2222222222', NULL, NULL);\n";
            sb.append(sql0).append(sql1);
            sb.append(sql0).append(sql2);
            sb.append(sql0).append(sql3);
            sb.append(sql0).append(sql4);
            sb.append(sql0).append(sql5);
            sb.append(sql0).append(sql6);
            sb.append(sql0).append(sql7);
            sb.append(sql0).append(sql8);
            sb.append(sql0).append(sql9);

        }
        try {
            testCreateFile(sb.toString(),"fengongsi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "2222";
    }



    @RequestMapping("/infojm")
    public String  getInfoJm() {
        String JMsql = "select user_id, type from user_info_mendian where type ='113' ;";
        String JMsql2 = "select user_id, type from user_info where type ='112' ;";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(JMsql);
        List<Map<String, Object>> list2 = jdbcTemplate.queryForList(JMsql2);
        StringBuffer sb = new StringBuffer();
        String sql0= "INSERT INTO `t_relation`(`id`, `app_alias`, `alias_flag`, `group_id`, `user_id`, `is_master`, `role_id`, `status`, `type`, `create_time`, `create_by`, `update_time`, `update_by`) ";
        int id = 70627;
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String user_id = String.valueOf(stringObjectMap.get("user_id"));
            id = id +1;
            // qtrsxy  pxxy  jc（zz）xy  hkdaxy   lhfwxy   fpxy   sxxy   sfbgxy  ld（w）ht
            String sql1 = "  VALUES ("+id+", 'ht_jm_yes_no', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '4444444444', NULL, NULL);\n";
            sb.append(sql0).append(sql1);

        }
        for (int i = 0; i <list2.size() ; i++) {
            Map<String, Object> stringObjectMap = list2.get(i);
            String user_id = String.valueOf(stringObjectMap.get("user_id"));
            id = id +1;
            // qtrsxy  pxxy  jc（zz）xy  hkdaxy   lhfwxy   fpxy   sxxy   sfbgxy  ld（w）ht
            String sql1 = "  VALUES ("+id+", 'ht_jm_yes_no', 'ht', NULL, '"+user_id+"', 0, 112, 1, NULL, '2022-01-30 10:30:00', '4444444444', NULL, NULL);\n";
            sb.append(sql0).append(sql1);

        }




        try {
            testCreateFile(sb.toString(),"hetongjimi");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "2222";
    }


    @RequestMapping("/infojbr")
    public String  getInfoJbr() {
        String sql = "select user_id, type from user_info20220208 where type ='109' ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        StringBuffer sb = new StringBuffer();
        String sql0= "INSERT INTO `t_relation`(`id`, `app_alias`, `alias_flag`, `group_id`, `user_id`, `is_master`, `role_id`, `status`, `type`, `create_time`, `create_by`, `update_time`, `update_by`) ";
        int id = 71588;
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String user_id = String.valueOf(stringObjectMap.get("user_id"));
            id = id +1;
            String sql1 = "  VALUES ("+id+", NULL, NULL, NULL, '"+user_id+"', 0, 109, 1, NULL, '2022-02-08 10:00:00', '4444444444', NULL, NULL);\n";
            sb.append(sql0).append(sql1);
        }
        try {
            testCreateFile(sb.toString(),"jbr");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "33333" ;

    }

    @RequestMapping("/infoMd")
    public String  getInfoMd() {
        String sql = "select user_id, type from user_info20220208 where type ='109' ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        StringBuffer sb = new StringBuffer();
        String sql0= "INSERT INTO `t_relation`(`id`, `app_alias`, `alias_flag`, `group_id`, `user_id`, `is_master`, `role_id`, `status`, `type`, `create_time`, `create_by`, `update_time`, `update_by`) ";
        int id = 68193;
        for (int i = 0; i <list.size() ; i++) {
            Map<String, Object> stringObjectMap = list.get(i);
            String user_id = String.valueOf(stringObjectMap.get("user_id"));
            id = id +1;
            // qtrsxy  pxxy  jc（zz）xy  hkdaxy   lhfwxy   fpxy   sxxy   sfbgxy  ld（w）ht
            String sql1 = "  VALUES ("+id+", 'qtrsxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql2 = "  VALUES ("+id+", 'pxxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql3 = "  VALUES ("+id+", 'jc（zz）xy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql4 = "  VALUES ("+id+", 'hkdaxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql5 = "  VALUES ("+id+", 'lhfwxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql6 = "  VALUES ("+id+", 'fpxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql7 = "  VALUES ("+id+", 'sxxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql8 = "  VALUES ("+id+", 'sfbgxy', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            id = id +1;
            String sql9 = "  VALUES ("+id+", 'ld（w）ht', 'ht', NULL, '"+user_id+"', 0, 113, 1, NULL, '2022-01-30 10:30:00', '3333333333', NULL, NULL);\n";
            sb.append(sql0).append(sql1);
            sb.append(sql0).append(sql2);
            sb.append(sql0).append(sql3);
            sb.append(sql0).append(sql4);
            sb.append(sql0).append(sql5);
            sb.append(sql0).append(sql6);
            sb.append(sql0).append(sql7);
            sb.append(sql0).append(sql8);
            sb.append(sql0).append(sql9);

        }
        try {
            testCreateFile(sb.toString(),"mendian");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "2222";
    }

    public static void   testCreateFile(String token,String name ) throws IOException {
        String fileName = "src/main/resources/"+name+".txt";
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
