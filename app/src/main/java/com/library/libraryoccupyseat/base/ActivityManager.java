package com.library.libraryoccupyseat.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.LinkedList;
import java.util.List;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class ActivityManager extends Application{

    private List<Activity> mActivityList = new LinkedList<>();
    private static ActivityManager instance = null;
    private static Context mContext ;
//    getApplicationContext().getFilesDir()

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public Context getmContext(){
       if(null != mContext){
           mContext=getApplicationContext();
       }
       return mContext;
   }

    private ActivityManager(){

    }

    public static ActivityManager getInstance(){
        if(null == instance){
            instance = new ActivityManager();
        }
        return instance;
    }

    //添加一个Activity到mActivityList集合当中
    public void addActivity(Activity activity){
        if(null != mActivityList){
            mActivityList.add(activity);
        }
    }

    //从mActivityList集合当中移除当前的Activity
    public Activity removeActivity(){
        return mActivityList.isEmpty()?null:mActivityList.remove(mActivityList.size()-1);
    }

    public Activity firstActivity(){
        return mActivityList.isEmpty()?null:mActivityList.get(0);
    }

    public Activity lastActivity(){
        return mActivityList.isEmpty()?null:mActivityList.get(mActivityList.size()-1);
    }

    public void finishAllActivity(){
        if(!mActivityList.isEmpty()){
            for (Activity activity : mActivityList) {
                activity.finish();
            }
        }
        mActivityList.clear();
    }

    public void finishCurrentActivity(){
        if(!mActivityList.isEmpty()){
            removeActivity().finish();
        }
    }

    public void finishActivity(Activity activity){
        if(!mActivityList.isEmpty()){
            activity.finish();
            mActivityList.remove(activity);
        }
    }

    public Activity getCurrentActivity(){
        return mActivityList.isEmpty()?null:mActivityList.get(mActivityList.size()-1);
    }


    /**
     * 判断软键盘是否弹出
     */
    public static boolean isSHowKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm.hideSoftInputFromWindow(v.getWindowToken(), 0)) {
            imm.showSoftInput(v, 0);
            return true;
            //软键盘已弹出
        } else {
            return false;
            //软键盘未弹出
        }
    }


}
