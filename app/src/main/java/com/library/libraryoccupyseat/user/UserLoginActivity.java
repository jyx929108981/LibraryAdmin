package com.library.libraryoccupyseat.user;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.library.libraryoccupyseat.admin.AdminMainActivity;
import com.library.libraryoccupyseat.student.MainActivity;
import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseActivity;
import com.library.libraryoccupyseat.utils.Md5Utils;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */
public class UserLoginActivity extends BaseActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btGo;
    private CardView cv;
    private FloatingActionButton fab;
    private View view;
    private String strUserName;
    private String strUserPassword;
    private int userType = 1;

    @Override
    protected BaseActivity getSelfActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        view = LayoutInflater.from(getSelfActivity()).inflate(R.layout.activity_user_login,null);

        {
            Intent intent = getIntent();
            userType = intent.getIntExtra("userType",userType);
        }

        setContentView(view);
        initView();
        btnClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        etUsername.setText("");
        etPassword.setText("");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btGo = (Button) findViewById(R.id.bt_go);
        cv = (CardView) findViewById(R.id.cv);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        //管理员账户内置，不需要注册新用户
        if (userType == 0){
            fab.setVisibility(View.INVISIBLE);
        }else{
            fab.setVisibility(View.VISIBLE);
        }

    }

    private void btnClickListener() {
        fab.setOnClickListener(onClickListener);
        btGo.setOnClickListener(onClickListener);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab:
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options =
                                ActivityOptions.makeSceneTransitionAnimation(getSelfActivity(), fab, fab.getTransitionName());
                        startActivity(new Intent(getSelfActivity(), UserRegisterActivity.class), options.toBundle());
                    } else {
                        startActivity(new Intent(getSelfActivity(), UserRegisterActivity.class));
                    }
                    break;
                case R.id.bt_go:
                    Explode explode = new Explode();
                    explode.setDuration(500);

                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);

                    strUserName = etUsername.getText().toString();
                    strUserPassword = etPassword.getText().toString();
                    if (!TextUtils.isEmpty(strUserName)) {
                        if (!TextUtils.isEmpty(strUserPassword)) {
                            strUserPassword = Md5Utils.md5(strUserPassword);
                            BmobUser bu2 = new BmobUser();
                            bu2.setUsername(strUserName);
                            bu2.setPassword(strUserPassword);
                            bu2.login(new SaveListener<BmobUser>() {

                                @Override
                                public void done(BmobUser bmobUser, BmobException e) {
                                    if(e==null){
                                        ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(getSelfActivity());
                                        Intent i2 = new Intent(getSelfActivity(), MainActivity.class);

                                        if (userType == 0){
                                            getSelfActivity().startAcitvityForNoIntent(AdminMainActivity.class);
                                        }else{
                                            getSelfActivity().startAcitvityForNoIntent(MainActivity.class);
                                        }
                                        getSelfActivity().finish();
                                    }else{
                                        Toast.makeText(getSelfActivity(), "登录失败"+e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getSelfActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getSelfActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }
    };
}
