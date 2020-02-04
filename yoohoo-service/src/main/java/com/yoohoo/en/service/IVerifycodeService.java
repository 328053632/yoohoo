package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TVerifycode;

public interface IVerifycodeService
{
    /**
     * 查询验证码
     * @param msisdn
     * @param code
     * @param serviceType
     * @return
     */
    TVerifycode query(String msisdn, String code, int serviceType);

    /**
     * 插入验证码
     * @param verifycode
     * @return
     */
    TVerifycode insert(TVerifycode verifycode);

    /**
     * 校验验证码是否有效
     * @param msisdn
     * @param code
     * @return
     */
    boolean checkCode(String msisdn, String code, int serviceType);
}
