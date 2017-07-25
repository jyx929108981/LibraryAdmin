package com.library.libraryoccupyseat.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.library.libraryoccupyseat.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class BaseFragment extends Fragment {

    public int mScreenWidth = 0;
    public int mScreenHeight = 0;

    //获取依附的Activity
    protected Activity getSelfActivity() {
        return getActivity();
    }

    //@SuppressLint("InlinedApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(getSelfActivity());
        getWindowWH();

/*
//沉浸式适配
       getActivity().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
              if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                     Window window = getActivity().getWindow();
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


    }

    //获取窗体的宽高
    private void getWindowWH() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;

    }

    public void back(View backView) {
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().finishCurrentActivity();
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });
    }

    protected void showToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
//        MyToast.getInstence().showInfoToast(content);
    }

    public void startActivityForNoIntent(Class forwordClass) {
        Intent intent = new Intent(getSelfActivity(), forwordClass);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    public void startActivityForIntent(Class forwordClass, Intent intent) {
        intent.setClass(getSelfActivity(), forwordClass);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    //获取窗体的宽度
    protected int getWindowWidth() {
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        return display.getWidth();
    }

    //获取窗体的高度
    protected int getWindowHeight() {
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        return display.getHeight();
    }

    /**
     * px 转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    protected int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;//获取当前手机分辨率
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dip 转为px
     *
     * @param context
     * @param dipValue
     * @return
     */
    protected int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    protected void finishAllActivity() {
        ActivityManager.getInstance().finishAllActivity();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }


    public static byte[] md5(String strDisplay) {
        return md5Ser(strDisplay.getBytes());
    }

    public static byte[] md5Ser(byte[] value) {
        try {
            MessageDigest ex = MessageDigest.getInstance("MD5");
            ex.update(value);
            return ex.digest();
        } catch (NoSuchAlgorithmException var2) {
            return null;
        }
    }

    public static String toHexString(byte[] digest) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < digest.length; ++i) {
            String hex = Integer.toHexString(digest[i] & 255);
            if (hex.length() < 2) {
                buffer.append(0);
            }
            buffer.append(hex);
        }
        return buffer.toString();
    }


}
