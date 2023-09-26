package com.myhd.service.Impl;


import com.myhd.dao.EnterpriseDao;
import com.myhd.pojo.Enterprise;
import com.myhd.service.EnterpriseService;
import com.myhd.util.MyBatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @description 企业服务层实现，实现以下功能：
 * 1. 企业信息认证插入数据库功能
 * 2. 回显数据功能
 * 3. 添加供应商时，在添加界面展示信息功能
 * @author JoneElmo
 * @date 2023-09-24 08:44
 * @version 1.0
 * @package com.myhd.service.Impl
 * @class EnterpriseServiceImpl
 */
@Slf4j
public class EnterpriseServiceImpl implements EnterpriseService {
    public static final  SqlSession session =  MyBatisUtil.singleSession(true);
    private EnterpriseDao dao = session.getMapper(EnterpriseDao.class);

    /**
     * @description 在企业信息认证界面，输入认证信息后，将认证信息插入到数据库，调用此方法
     * @author JoneElmo
     * @date 2023-09-24 09:12
     * @param enterprise
     * @return java.lang.Boolean
     */
    @Override
    public Boolean addEnterprise(Enterprise enterprise) {
        try {
            Integer i = dao.insertEnterprise(enterprise);
            return true;
        } catch (PersistenceException e) {
            log.error("插入数据重复");
            return false;
        }catch (NullPointerException e){
            log.error("存在字段为空,请检查字段值");
            return false;
        }
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

    /**
     * @description: 判断企业等名称，信用代码，企业邮箱是否重复,如果返回值为true则认为数据重复
     * @param enterprise
     * @return: java.lang.Boolean
     * @author CYQH
     * @date: 2023/09/26 8:45
     */
    @Override
    public Boolean judgeEnterpriseInfoIsExists(Enterprise enterprise) {
        return dao.countEnterpriseInfo(enterprise) == 1;
    }

}
