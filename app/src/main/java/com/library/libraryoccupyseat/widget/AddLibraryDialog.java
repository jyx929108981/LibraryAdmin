package com.library.libraryoccupyseat.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.library.libraryoccupyseat.R;

/**
 * @author jyx
 * Created by weisi on 2017/7/21.
 */

public class AddLibraryDialog {

    private Context context;
    private AlertDialog mAlertDialog;
    private View view;
    private EditText dialog_first_edt,dialog_second_edt,dialog_third_edt;

    public AddLibraryDialog(Context context) {
        this.context = context;
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.show();
        view = LayoutInflater.from(context).inflate(R.layout.dialog_add_library, null);
        mAlertDialog.getWindow().setContentView(view);
        mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mAlertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {

    }
}
