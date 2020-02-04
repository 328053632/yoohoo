package com.yoohoo.en.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class GenericTools
{
    public static <T> List<T> createList()
    {
        return new ArrayList<T>();
    }

    public static <T> List<T> createList(int n)
    {
        return new ArrayList<T>(n);
    }

    public static <T, S> Map<T, S> createMap()
    {
        return new HashMap<T, S>();
    }

    public static <T, S> Map<T, S> createMap(int n)
    {
        return new HashMap<T, S>(n);
    }

    public static <T> List<T> createSafeList()
    {
        return new CopyOnWriteArrayList<T>();
    }

    public static <T, S> Map<T, S> createSafeMap()
    {
        return new ConcurrentHashMap<T, S>();
    }

    public static <T> Set<T> createSafeSet()
    {
        return new CopyOnWriteArraySet<T>();
    }

    public static <T> Set<T> createSet()
    {
        return new HashSet<T>();
    }

    public static <T> Set<T> createSet(int n)
    {
        return new HashSet<T>(n);
    }
}
