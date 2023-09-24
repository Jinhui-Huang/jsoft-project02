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
     * @description 添加认证企业
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
     * @description 根据企业id获取企业信息。
     * @author JoneElmo
     * @date 2023-09-24 09:12
     * @param enterpriseId
     * @return com.myhd.pojo.Enterprise
     */
    @Override
    public Enterprise selectByEnterpriseId(Integer enterpriseId) {
        return dao.selectByEnterpriseId(enterpriseId);
    }

    @Override
    public Enterprise selectEnterpriseExceptWhiteAndBlack(Integer enterpriseId) {
        return null;
    }

    @Override
    public Enterprise selectEnterpriseExceptBlack(Integer enterpriseId) {
        return null;
    }
}
