package com.study.ht.studyandroid.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 用于控制控件状态
 * Created by Seven on 2016/7/22.
 */

public class RxViewUtils {

    /**
     * 点击事件
     *
     * @param view
     * @return
     */
    public static Observable<Object> click(View view) {
        return RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }


    /**
     * 是否可用事件
     *
     * @param view
     * @param enable
     */
    public static void enable(View view, boolean enable) {
        try {
            RxView.enabled(view).accept(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否选择事件
     *
     * @param view
     * @param selected
     */
    public static void selected(View view, boolean selected) {
        try {
            RxView.selected(view).accept(selected);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 隐藏显示
     *
     * @param view
     * @param visibility
     */
    public static void visibility(View view, boolean visibility) {
        try {
            RxView.visibility(view).accept(visibility);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否是可见状态
     *
     * @param view
     * @return
     */
    public static boolean isVisibility(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    /**
     * 文本改变事件
     *
     * @param textView
     * @return
     */
    public static Observable<CharSequence> textChange(TextView textView) {
        return RxTextView.textChanges(textView);
    }

    /**
     * 改变事件  用于checkBox
     *
     * @param button
     * @return
     */
    public static Observable<Boolean> checkChange(CompoundButton button) {
        return RxCompoundButton.checkedChanges(button);
    }

    /**
     * 列表点击事件 用于listview,gridview
     *
     * @param adapterView
     * @return
     */
    public static Observable<Integer> itemClick(AdapterView adapterView) {
        return RxAdapterView.itemClicks(adapterView).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    /**
     * 列表长按事件
     *
     * @param adapterView
     * @return
     */
    public static Observable<Integer> longItemClick(AdapterView adapterView) {
        return RxAdapterView.itemLongClicks(adapterView).throttleFirst(500, TimeUnit.MILLISECONDS);
    }

    /**
     * 长按事件
     *
     * @param view
     * @return
     */
    public static Observable<Object> longClick(View view) {
        return RxView.longClicks(view).throttleFirst(500, TimeUnit.MILLISECONDS);
    }
}
