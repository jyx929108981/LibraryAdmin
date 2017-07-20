package com.library.libraryoccupyseat.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseActivity;
import com.library.libraryoccupyseat.utils.sharedprederence.SharedPreferenceCode;

import cn.bmob.v3.Bmob;

/**
 * @author jyx
 *
 * Created by weisi on 2017/2/20.
 */
public class WelcomeActivity extends BaseActivity {

    private View view;
    private CardView welcome_admi_cardView;
    private CardView welcome_student_cardView;
    private SharedPreferences shardPreferences;

    @Override
    protected void initContentViews() {
        super.initContentViews();

        //初始化Bmob
        Bmob.initialize(this, "e9dc7b5492a295ab308ad28ff14d1b64");

        view = LayoutInflater.from(getSelfActivity()).inflate(R.layout.activity_welcome,null);
        setContentView(view);
        initView();
        setCardBtnListener();
        shardPreferences = getSharedPreferences("library",MODE_PRIVATE);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        welcome_student_cardView = (CardView) view.findViewById(R.id.welcome_student_cardView);
        welcome_admi_cardView = (CardView) view.findViewById(R.id.welcome_admi_cardView);
    }

    /**
     * 按钮点击事件监听
     */
    private void setCardBtnListener() {
        final Intent intent = new Intent();

        //管理员身份进入
        welcome_admi_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shardPreferences.edit().putInt(SharedPreferenceCode.USER_TYPE,0);//记录当前用户类型
                intent.putExtra("userType",0);
                getSelfActivity().startActivityForIntent(UserLoginActivity.class,intent);
                getSelfActivity().finish();
            }
        });

        //学员身份进入
        welcome_student_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shardPreferences.edit().putInt(SharedPreferenceCode.USER_TYPE,1);//记录当前用户类型
                intent.putExtra("userType",1);
                getSelfActivity().startActivityForIntent(UserLoginActivity.class,intent);
                getSelfActivity().finish();
            }
        });
    }

    @Override
    protected BaseActivity getSelfActivity() {
        return this;
    }

}
