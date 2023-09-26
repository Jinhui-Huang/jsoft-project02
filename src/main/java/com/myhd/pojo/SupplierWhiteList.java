package com.myhd.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * className SupplierWhiteList
 * packageName com.myhd.pojo
 * Description 白名单表实体类，主键id，企业主键id enterpriseId，供应商主键id supplierId，企业评级supplierLevel，更新日期updateDate；
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierWhiteList {
    private Integer id;/*自增主键*/
    private Integer enterpriseId;/*企业主键id*/
    private Integer supplierId;/*供应商主键id*/
    private String supplierLevel;/*企业评级*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateDate;/*更新日期*/
}
