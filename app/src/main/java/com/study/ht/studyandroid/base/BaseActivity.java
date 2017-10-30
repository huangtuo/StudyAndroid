package com.study.ht.studyandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.study.ht.studyandroid.StudyApplication;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author huangtuo
 * @time 2017/10/23.
 */

public class BaseActivity extends RxAppCompatActivity{

    protected StudyApplication application;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = StudyApplication.getInstance();
        unbinder = ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
    }


    @Override
    protected void onDestroy() {
        if(unbinder != null)
            unbinder.unbind();
        super.onDestroy();
    }
}
