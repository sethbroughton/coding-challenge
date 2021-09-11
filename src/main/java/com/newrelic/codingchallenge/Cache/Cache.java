package com.newrelic.codingchallenge.Cache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static ConcurrentHashMap<String, Boolean> nonDuplicateSet;
    private static Cache instance = null;

    private Cache() {
        nonDuplicateSet = new ConcurrentHashMap();
    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public boolean add(String number) {
        return nonDuplicateSet.putIfAbsent(number, true)==null;
    }
}
