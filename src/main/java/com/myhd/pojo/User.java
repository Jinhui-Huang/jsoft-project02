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
    private Integer id;/*自增主键*/
    private String name;/*用户名*/
    private Integer enterpriseId;/*企业id*/
    private String enterpriseName;/*企业名称*/
    private String phone;/*用户电话*/
    private String account;/*账号*/
    private String password;/*密码*/
    private String idcardName;/*身份证姓名*/
    private String idcardNo;/*身份证号码*/

}
