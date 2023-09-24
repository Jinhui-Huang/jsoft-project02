package com.myhd.service.Impl;


import com.myhd.dao.EnterpriseDao;
import com.myhd.pojo.Enterprise;
import com.myhd.service.EnterpriseService;
import com.myhd.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @description 企业服务层实现
 * @author JoneElmo
 * @date 2023-09-24 08:44
 * @version 1.0
 * @package com.myhd.service.Impl
 * @class EnterpriseServiceImpl
 */
public class EnterpriseServiceImpl implements EnterpriseService {
    SqlSession session = MyBatisUtil.openSession(true);
    EnterpriseDao dao = session.getMapper(EnterpriseDao.class);

    /**
     * @description 在企业信息认证界面，输入认证信息后，将认证信息插入到数据库，调用此方法
     * @author JoneElmo
     * @date 2023-09-24 09:12
     * @param enterprise
     * @return java.lang.Boolean
     */
    @Override
    public Boolean addEnterprise(Enterprise enterprise) {
        return dao.insertEnterprise(enterprise)==1?true:false;
    }

    /**
     * @description 根据企业id获取企业信息。用于回显数据到信息认证界面
     * @author JoneElmo
     * @date 2023-09-24 09:12
     * @param enterpriseId
     * @return com.myhd.pojo.Enterprise
     */
    @Override
    public Enterprise selectByEnterpriseId(Integer enterpriseId) {
        return dao.selectByEnterpriseId(enterpriseId);
    }

    /**
     * @description 查询企业信息。白名单添加供应商时，展示下拉列表信息和统一社会信用代码信息
     * @author JoneElmo
     * @date 2023-09-24 09:58
     * @param enterpriseId
     * @return java.util.List<com.myhd.pojo.Enterprise>
     */
    @Override
    public List<Enterprise> selectEnterpriseExceptWhiteAndBlack(Integer enterpriseId) {
        return dao.selectEnterpriseExceptWhiteAndBlack(enterpriseId);
    }

    /**
     * @description 查询企业信息。黑名单添加供应商时，展示下拉列表信息和统一社会信用代码信息
     * @author JoneElmo
     * @date 2023-09-24 09:40
     * @param enterpriseId
     * @return java.util.List<com.myhd.pojo.Enterprise>
     */
    @Override
    public List<Enterprise> selectEnterpriseExceptBlack(Integer enterpriseId) {
        return dao.selectEnterpriseExceptBlack(enterpriseId);
    }

}
