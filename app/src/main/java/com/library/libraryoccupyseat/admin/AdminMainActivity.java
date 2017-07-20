package com.library.libraryoccupyseat.admin;

import android.os.Bundle;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseActivity;

/**
 * 管理员操作系统
 */
public class AdminMainActivity extends BaseActivity {

    @Override
    protected BaseActivity getSelfActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mian);
    }
}
