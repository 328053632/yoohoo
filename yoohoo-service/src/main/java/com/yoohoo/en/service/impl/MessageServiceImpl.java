package com.yoohoo.en.service.impl;

import com.yoohoo.en.dao.mapper.TMessageMapper;
import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TMessageExample;
import com.yoohoo.en.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService
{

    @Autowired
    private TMessageMapper messageMapper;

    @Override
    public TMessage query(int messageId)
    {
        return messageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public List<TMessage> query(TMessageExample example)
    {
        return messageMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int count(TMessageExample example)
    {
        return messageMapper.countByExample(example);
    }

    @Override
    public int update(TMessage message)
    {
        return messageMapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public int update(TMessage message, TMessageExample example)
    {
        return messageMapper.updateByExampleSelective(message, example);
    }

    @Override
    public int delete(TMessageExample example)
    {
        return messageMapper.deleteByExample(example);
    }

    @Override
    public int insert(TMessage message)
    {
        messageMapper.insert(message);
        return message.getmId();
    }
}
