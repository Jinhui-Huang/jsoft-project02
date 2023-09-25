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
    private Integer id;/*被查询的企业id*/
    private String enterpriseName;/*模糊查询时输入的企业名字或包含的字段*/
    private String supplierLevel;/*白名单模糊查询时的企业评级，黑名单时不需要*/
    private Integer startPage = 1;/*查询的某一页的页数*/
    private Integer pageSize = 5;/*每页展示多少条数据*/
}
