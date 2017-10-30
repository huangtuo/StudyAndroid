package com.study.ht.studyandroid.utils;/**
 * Created by ttarfall on 2016/9/9.
 */

import android.content.Context;
import android.widget.Toast;

/**
 * @author ttarfall
 * @date 2016-09-09 17:07
 */
public class ToastUtil {

    public static void show(Context context, CharSequence msg) {
        if (!StringUtil.isEmpty(msg) && context != null)
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, CharSequence msg) {
        if (!StringUtil.isEmpty(msg)&& context != null)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context, CharSequence msg, int gravity) {
        if (!StringUtil.isEmpty(msg)&& context != null) {
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        }
    }

    public static void showShort(Context context, CharSequence msg, int gravity) {
        if (!StringUtil.isEmpty(msg)&& context != null) {
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setGravity(gravity, 0, 0);
            toast.show();
        }
    }
}
