package com.library.libraryoccupyseat.user;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */
public class UserRegisterActivity extends BaseActivity {
    private FloatingActionButton fab;
    private CardView cvAdd;
    private Button btGo;
    private View view;
    private String user_name;
    private String user_password;
    private String user_repeat_password;
    private EditText et_username;
    private EditText et_password;
    private EditText et_repeatpassword;

    @Override
    protected BaseActivity getSelfActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getSelfActivity()).inflate(R.layout.activity_user_register, null);
        setContentView(view);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    private void initView() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);
        btGo = (Button) findViewById(R.id.bt_go);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        et_repeatpassword = (EditText) findViewById(R.id.et_repeatpassword);
        btGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = et_username.getText().toString();
                user_password = et_password.getText().toString();
                user_repeat_password = et_repeatpassword.getText().toString();
                if (!TextUtils.isEmpty(user_name)) {
                    if (!TextUtils.isEmpty(user_password)) {
                        if (!TextUtils.isEmpty(user_repeat_password)) {
                            if (user_password.equals(user_repeat_password)) {

                                BmobUser bu = new BmobUser();
                                bu.setUsername(user_name);
                                bu.setPassword(user_password);
                                //注意：不能用save方法进行注册
                                bu.signUp(new SaveListener<BmobUser>() {
                                    @Override
                                    public void done(BmobUser s, BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(getSelfActivity(), "注册成功", Toast.LENGTH_SHORT).show();
                                            animateRevealClose();

                                        } else {
                                            Toast.makeText(getSelfActivity(), "注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getSelfActivity(), "两次密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getSelfActivity(), "请输入再次输入密码", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getSelfActivity(), "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getSelfActivity(), "请输入用户名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                UserRegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }

}
