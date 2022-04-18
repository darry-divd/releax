package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
public class IndexController {


    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "JavaFeng";
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {



    }
}
