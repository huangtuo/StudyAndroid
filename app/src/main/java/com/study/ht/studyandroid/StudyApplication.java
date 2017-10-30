package com.study.ht.studyandroid;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * @author huangtuo
 * @time 2017/10/23.
 */

public class StudyApplication extends Application {

    private static StudyApplication application;
    private List<Activity> mActList = new LinkedList<>();

    public static StudyApplication getInstance(){
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public void addActivity(Activity activity){
        mActList.add(activity);
    }

    private void exit(){
        try{
            for(Activity activity : mActList){
                if(activity != null){
                    activity.finish();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.exit(0);
        }
    }
}
