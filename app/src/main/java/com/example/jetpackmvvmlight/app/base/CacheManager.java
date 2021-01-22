package com.example.jetpackmvvmlight.app.base;

import android.os.Environment;

import com.example.jetpackmvvmlight.APP;
import com.example.jetpackmvvmlight.BuildConfig;
import com.example.jetpackmvvmlight.app.utils.MD5;
import com.example.jetpackmvvmlight.app.utils.StreamUtil;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CacheManager {

    private DiskLruCache mDiskLruCache;
    private final int maxSize = 20;

    private CacheManager() {
        try {
            File cacheDir = new File(getCacheDir());
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, BuildConfig.VERSION_CODE, 1, maxSize * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHolder {
        private static final CacheManager INSTANCE = new CacheManager();
    }

    public static CacheManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 保存
     *
     * @param key
     * @param data
     * @return
     */
    public boolean saveCache(String key, Object data) {
        try {
            DiskLruCache.Editor edit = mDiskLruCache.edit(getKey(key));

            OutputStream fos = edit.newOutputStream(0);

            if (StreamUtil.writeToStream(fos, data)) {
                edit.commit();
            } else {
                edit.abort();
            }
            mDiskLruCache.flush();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取出
     *
     * @param key
     * @param cls
     * @param <D>
     * @return
     */
    public <D> ArrayList<D> getCacheList(String key, Class<D> cls) {
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(getKey(key));
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                return StreamUtil.readListStream(inputStream, cls);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    /**
     * 删除某个key
     *
     * @param key
     * @return
     */
    public boolean remove(String key) {

        if (mDiskLruCache != null) {
            try {
                return mDiskLruCache.remove(getKey(key));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 关闭缓存
     */
    public void clear() {
        if (mDiskLruCache != null) {
            try {
                mDiskLruCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * MD5加密key
     *
     * @param key
     * @return
     */
    public String getKey(String key) {
        String fileName = "cache_" + key;
        return MD5.encodeKey(fileName);
    }


    public String getCacheDir() {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = APP.instance.getExternalCacheDir().getPath();
        } else {
            cachePath = APP.instance.getCacheDir().getPath();
        }
        return cachePath;
    }
}
