package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 功能描述
 *
 * @author guacnong
 * @date $
 */
@Entity(name = "t_contract")
public class t_contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String  code;//'合同编号'
    String  code_type;//编号类型-0手动编号,1自动编号
    String  code_dept_id;//'编号部门id
    String  name;//'合同名称'
    String  version;//'版本号'
    String  money;//'合同金额'
    String  party_A;
    String  party_B;
    String  party_C;
    String  party_D;
    Date signed_time;
    String  start_stop_state;
    Date  start_time;
    Date  end_time;
    String  type_id;
    String  state;
    String  secret;
    String  payment_ratio;
    Date  create_time;
    Date  update_time;
    Date  audit_time;
    String  handle_deptId;
    String  handle_deptName;
    String  handle_userId;
    String  handle_userName;
    String  remark;


}
