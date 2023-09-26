package com.myhd.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer id;/*自增主键*/
    private Integer enterpriseId;/*企业主键id*/
    private Integer supplierId;/*供应商主键id*/
    private String reason;/*添加理由*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date updateDate;/*更新日期*/
}
