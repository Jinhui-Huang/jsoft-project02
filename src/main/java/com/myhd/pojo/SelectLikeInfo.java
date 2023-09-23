package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className SelectLikeInfo
 * packageName com.myhd.pojo
 * Description TODO 模糊查询时用于存储查询条件的实体类
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/23 16:12
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectLikeInfo {
    private Integer id;
    private String enterpriseName;
    private String supplierLevel;
}
