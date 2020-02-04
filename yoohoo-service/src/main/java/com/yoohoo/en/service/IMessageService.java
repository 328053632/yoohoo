package com.yoohoo.en.service;

import com.yoohoo.en.dao.model.TMessage;
import com.yoohoo.en.dao.model.TMessageExample;

import java.util.List;

public interface IMessageService
{
    TMessage query(int messageId);

    List<TMessage> query(TMessageExample example);

    int count(TMessageExample example);

    int update(TMessage message);

    int update(TMessage message, TMessageExample example);

    int delete(TMessageExample example);

    int insert(TMessage message);
}
