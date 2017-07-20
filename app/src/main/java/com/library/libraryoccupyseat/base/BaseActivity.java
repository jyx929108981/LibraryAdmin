package com.library.libraryoccupyseat.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.library.libraryoccupyseat.R;

/**
 * @author jyx
 *         Created by weisi on 2017/7/20.
 */
public abstract class BaseActivity extends Activity {

    protected abstract BaseActivity getSelfActivity();

    //   @SuppressLint("InlinedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

       /* Notification notification = new Notification(R.drawable.weijia, "Notification comes", System. currentTimeMillis());

        Intent notificationIntent = new Intent(this, UserLogin.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);

        startForeground(1, notification);

        Log.d("MyService", "onCreate executed");*/

//沉浸式适配
   /*  getWindow().requestFeature(Window.FEATURE_NO_TITLE);
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
                   }
*/


        ActivityManager.getInstance().addActivity(getSelfActivity());
        initContentViews();
        initContemtViewListeners();
    }

    protected void initContemtViewListeners() {
    }

    protected void initContentViews() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            getSelfActivity().finish();
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    public void back(View backView) {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishCurrentActivity();
            }
        });
    }

    public void finishCurrentActivity() {
        ActivityManager.getInstance().finishCurrentActivity();
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    //无Intent跳转到指定的activity
    public void startAcitvityForNoIntent(Class forwordClass) {
        Intent intent = new Intent(getSelfActivity(), forwordClass);
        startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    //有Intent跳转到指定的activity
    public void startActivityForIntent(Class forwordClass, Intent intent) {
        intent.setClass(getSelfActivity(), forwordClass);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    //带数据跳转到指定的activity
    public void startActivityForResult(Class forwordClass, Intent intent, int requestCode) {
        intent.setClass(getSelfActivity(), forwordClass);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }


}
