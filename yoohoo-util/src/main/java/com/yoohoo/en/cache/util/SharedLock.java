package com.yoohoo.en.cache.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 */
public class SharedLock
{
    public class RefLock implements LockHandle
    {
        private ReentrantLock lock = new ReentrantLock();
        
        private int refCount = 0;
        
        private String key;
        
        public RefLock(String key)
        {
            this.key = key;
        }
        
        /**
         * {@inheritDoc}
         */
        public void release()
        {
            releaseLock(this);
        }
    }
    
    private ReentrantLock mapLock = new ReentrantLock();
    
    private Map<String, RefLock> lockMap = new HashMap<String, RefLock>();
    
    private void releaseLock(RefLock lock)
    {
        lock.lock.unlock();
        try
        {
            mapLock.lock();
            lock.refCount--;
            if (lock.refCount == 0)
            {
                lockMap.remove(lock.key);
            }
        }
        finally
        {
            mapLock.unlock();
        }
    }
    
    public LockHandle lock(String key)
    {
        RefLock refLock = null;
        try
        {
            mapLock.lock();
            refLock = lockMap.get(key);
            if (refLock == null)
            {
                refLock = new RefLock(key);
                lockMap.put(key, refLock);
            }
            refLock.refCount++;
        }
        finally
        {
            mapLock.unlock();
        }
        
        refLock.lock.lock();
        return refLock;
    }
    
    public static void main(String[] args)
    {
        SharedLock lock = new SharedLock();
        LockHandle handle = lock.lock(null);
        handle.release();
    }
}