package com.yoohoo.en.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class YunmaThreadPool extends ThreadPoolExecutor
{
    public YunmaThreadPool(int corePoolSize, int maximumPoolSize,int blockingQueueSize)
    {
        super(corePoolSize, maximumPoolSize, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(blockingQueueSize, true));
        this.prestartAllCoreThreads();
    }
}
