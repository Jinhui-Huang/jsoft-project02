package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * className ThreeTablesQuery
 * packageName com.myhd.pojo
 * Description  用于存储黑/白名单页分页查询数据的三表联查实体类，user表【供应商id supplierId，企业名称EnterpriseName，企业联系人idcardName】enterprise表【企业联系方式phone，联系邮箱email】黑/白名单表【可变信息VariableInfo(企业评级level/添加原因reason)，更新日期updateDate】
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 20:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeTablesQuery {
    private Integer supplierId;
    private String enterpriseName;
    private String idcardName;
    private String phone;
    private String email;
    private String variableInfo;
    private Date updateDate;
}
