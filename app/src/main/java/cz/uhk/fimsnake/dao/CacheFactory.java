package cz.uhk.fimsnake.dao;

/**
 * Created by Luboš Hájek in 2019
 */
public class CacheFactory {

    private static MemoryCache memoryCache;

    public static Cache getInstance(){
        if (memoryCache == null) {
            memoryCache = new MemoryCache();
        }
        return memoryCache;
    }
}
