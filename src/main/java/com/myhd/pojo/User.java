package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className User
 * packageName com.myhd.pojo
 * Description 用户表实体类，主键id，用户名name，企业id enterpriseId，企业名称enterpriseName，用户电话phone，账号account，密码password，身份证姓名idcardName，身份证号码idcardNo。
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private String enterpriseId;
    private String enterpriseName;
    private String phone;
    private String account;
    private String password;
    private String idcardName;
    private String idcardNo;

}
