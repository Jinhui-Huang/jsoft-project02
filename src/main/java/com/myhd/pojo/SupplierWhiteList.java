package com.myhd.pojo;

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
    private Integer id;
    private Integer enterpriseId;
    private Integer supplierId;
    private String supplierLevel;
    private Date updateDate;
}
