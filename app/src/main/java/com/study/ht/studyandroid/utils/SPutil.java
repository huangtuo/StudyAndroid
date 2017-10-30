package com.study.ht.studyandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;


/**
 * @author ttarfall
 * @date 2016-09-23 11:18
 */
public class SPutil {


    private static SharedPreferences getPref(Context mContext, String name) {
        return mContext.getSharedPreferences(name, Context.MODE_PRIVATE);

    }


    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param value    保存的value
     */
    public static void putBoolean(Context mContext, String name, String key,
                                  boolean value) {

        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(Context mContext, String name, String key) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getBoolean(key, false);
    }

    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param value    保存的value
     */
    public static void putString(Context mContext, String name, String key,
                                 String value) {
        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, Base64.encodeToString(value.getBytes(), 0));
        editor.apply();
    }

    public static String getString(Context context, String name, String key) {

        return getString(context, name, key, "");
    }

    public static String getString(Context mContext, String name, String key, String defValue) {
        SharedPreferences preferences = getPref(mContext, name);
        return new String(Base64.decode(preferences.getString(key, defValue), 0));
    }

    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param value    保存的value
     */
    public static void putFloat(Context mContext, String name, String key,
                                Float value) {
        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.apply();

    }

    public static Float getFloat(Context mContext, String name, String key) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getFloat(key, 0f);
    }

    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param value    保存的value
     */
    public static void putInt(Context mContext, String name, String key,
                              int value) {
        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context mContext, String name, String key) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getInt(key, 0);
    }

    public static int getInt(Context mContext, String name, String key, int defalut) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getInt(key, defalut);
    }

    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param value    保存的value
     */
    public static void putLong(Context mContext, String name, String key,
                               Long value) {
        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static Long getLong(Context mContext, String name, String key) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getLong(key, 0);
    }

    /**
     * @param mContext
     * @param name     SharedPreferences的name
     * @param key      保存的key
     * @param values   保存的value
     */
    @SuppressLint("NewApi")
    public static void putStringSet(Context mContext, String name, String key,
                                    Set<String> values) {
        SharedPreferences preferences = getPref(mContext, name);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    @SuppressLint("NewApi")
    public static Set<String> getStringSet(Context mContext, String name,
                                           String key) {
        SharedPreferences preferences = getPref(mContext, name);
        return preferences.getStringSet(key, null);
    }


    public static boolean putObject(Context mContext, String filename, String keyName, Object object) {
        SharedPreferences preferences = getPref(mContext, filename);
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        String base = "";

        try {

            if (null != object) {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                base = Base64.encodeToString(baos.toByteArray(), 0);
            }
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(keyName, base);
            editor.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static boolean cleanObject(Context mContext, String filename, String keyName) {
        return putObject(mContext, filename, keyName, null);
    }

    public static Object getObject(Context mContext, String filename, String keyName) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            SharedPreferences mSharedPreferences = getPref(mContext, filename);
            String base = mSharedPreferences.getString(keyName, "");

            if (!TextUtils.isEmpty(base)) {
                byte[] base64Bytes = Base64.decode(base.getBytes(), 0);
                bais = new ByteArrayInputStream(base64Bytes);
                ois = new ObjectInputStream(bais);
                return ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != bais) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    /**
     * 删除
     *
     * @param mContext
     * @param fileName
     */
    public static void deleteSharedPreferences(Context mContext, String fileName) {
        File file = new File(mContext.getFilesDir().getParentFile().getPath() + "/shared_prefs", fileName + ".xml");
        if (file.exists()) {
            file.delete();
        }
    }
}
