package cz.uhk.fimsnake.dao;

import cz.uhk.fimsnake.dao.Cache;
import cz.uhk.fimsnake.dao.MemoryCache;

public class CacheFactory {

    private static MemoryCache memoryCache;

    public static Cache getInstance(){
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }
}
