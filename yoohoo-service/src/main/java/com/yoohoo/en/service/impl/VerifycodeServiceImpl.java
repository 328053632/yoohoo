package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.mapper.TVerifycodeMapper;
import com.yoohoo.en.dao.model.TVerifycode;
import com.yoohoo.en.dao.model.TVerifycodeExample;
import com.yoohoo.en.service.IVerifycodeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VerifycodeServiceImpl implements IVerifycodeService
{
    @Autowired
    private TVerifycodeMapper verifycodeMapper;

    @Override
    public TVerifycode query(String msisdn, String code, int serviceType)
    {
        TVerifycodeExample example = new TVerifycodeExample();
        example.createCriteria().andMsisdnEqualTo(msisdn).andVerifyCodeEqualTo(code);
        List<TVerifycode> tVerifycodes = verifycodeMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tVerifycodes))
        {
            return null;
        }
        return tVerifycodes.get(0);
    }

    @Override
    public TVerifycode insert(TVerifycode verifycode)
    {
        return null;
    }

    @Override
    public boolean checkCode(String msisdn, String code, int serviceType)
    {
        TVerifycode verifycode = query(msisdn, code, serviceType);
        if (null == verifycode)
        {
            return false;
        }

        Date now = new Date();

        if (verifycode.getCreateTime().before(now) && verifycode.getOverTime().after(now))
        {
            return true;
        }
        return false;
    }
}
