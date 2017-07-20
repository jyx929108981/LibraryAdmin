package com.library.libraryoccupyseat.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.library.libraryoccupyseat.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected abstract BaseFragmentActivity getSelfActivity();

    private static Boolean isExit = false;

    //  @SuppressLint("InlinedApi")
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        //设置沉浸式布局
        {
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                        | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.setNavigationBarColor(Color.TRANSPARENT);
            }
        }

       /* getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }*/
        ActivityManager.getInstance().addActivity(getSelfActivity());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            getSelfActivity().finish();
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }

    private void exitAppliaction() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; //准备退出
            Toast.makeText(getSelfActivity(), "再按一次退出程序", Toast.LENGTH_SHORT).show();


            tExit = new Timer();

            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            ActivityManager.getInstance().finishAllActivity();
            android.app.ActivityManager manger = (android.app.ActivityManager) getSelfActivity().getSystemService(Context.ACTIVITY_SERVICE);
            manger.killBackgroundProcesses(getSelfActivity().getPackageName());
            System.exit(0);
        }
    }

    public void back(View backView) {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().finishCurrentActivity();
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }

    protected void showToast(String content) {
        Toast.makeText(getSelfActivity(), content, Toast.LENGTH_SHORT).show();
    }

    protected void startActivityForNoIntent(Class forwordClass) {
        Intent intent = new Intent(getSelfActivity(), forwordClass);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    public void startActivityForIntent(Class forwordClass, Intent intent) {
        intent.setClass(getSelfActivity(), forwordClass);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    protected void finishAllActivity() {
        ActivityManager.getInstance().finishAllActivity();
    }

}




