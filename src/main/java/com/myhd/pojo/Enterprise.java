package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className Enterprise
 * packageName com.myhd.pojo
 * Description 企业表实体类，主键id，企业名称name，社会统一代码socialUniformCode，邮箱email，企业联系人电话phone，企业注册详细地址address，企业规模scale，传真fax。
 *
 * @author "CYQH"
 * @version 1.0
 * @email 1660855825@qq.com
 * @Date: 2023/09/22 18:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    private Integer id;
    private String name;
    private String socialUniformCode;
    private String email;
    private String phone;
    private String address;
    private String scale;
    private String fax;
}
