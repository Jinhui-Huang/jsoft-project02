package com.myhd.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * className ThreeTablesQuery
 * packageName com.myhd.pojo
 * Description  用于存储黑/白名单页分页查询数据的三表联查实体类，user表【企业联系人idcardName，联系方式phone】enterprise表【企业名称EnterpriseName，联系邮箱email】黑/白名单表【供应商id supplierId，可变信息VariableInfo(企业评级level/添加原因reason)，更新日期updateDate】
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
    private Integer supplierId;/*供应商*/
    private String enterpriseName;/*企业名称*/
    private String idcardName;/*企业联系人*/
    private String phone;/*联系方式*/
    private String email;/*联系邮箱*/
    private String variableInfo;/*填入内容可变信息*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateDate;/*更新日期*/
}
