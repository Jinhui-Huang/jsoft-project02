package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * className SupplierBlackList
 * packageName com.myhd.pojo
 * Description 黑名单表实体类，主键id，企业主键id enterpriseId，供应商主键id supplierId，添加理由reason，更新日期updateDate；
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierBlackList {
    private Integer id;
    private Integer enterpriseId;
    private Integer supplierId;
    private String reason;
    private Date updateDate;
}
