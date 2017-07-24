package com.library.libraryoccupyseat.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.bean.AdminSetBean;

/**
 * @author jyx
 *         Created by weisi on 2017/7/21.
 */

public class AddLibraryDialog {

    private Context context;
    private AlertDialog mAlertDialog;
    private View view;
    private EditText dialog_library_name, dialog_library_floor, dialog_library_width;
    private Button dialog_btn_cancel, dialog_btn_affirm;

    private OnCancelListener onCancelListener;
    private OnPositiveListener onPositiveListener;

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
        setBtnListener();
    }

    private void setBtnListener() {
        dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelListener!= null){
                    onCancelListener.ClickListener(v);
                }
            }
        });

        dialog_btn_affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener!= null){
                    onPositiveListener.ClickListener(v);
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        dialog_library_name = (EditText) view.findViewById(R.id.dialog_library_name);
        dialog_library_floor = (EditText) view.findViewById(R.id.dialog_library_floor);
        dialog_library_width = (EditText) view.findViewById(R.id.dialog_library_width);
        dialog_btn_cancel = (Button) view.findViewById(R.id.dialog_btn_cancel);
        dialog_btn_affirm = (Button) view.findViewById(R.id.dialog_btn_affirm);
    }

    public AdminSetBean getLibraryInfo(){
        AdminSetBean adminSetBean = new AdminSetBean();

        return adminSetBean;
    }


    public interface OnCancelListener{
        void ClickListener(View view);
    }

    public void setOnCancelListener(AddLibraryDialog.OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public interface OnPositiveListener{
        void ClickListener(View view);
    }

    public void setOnPositiveListener(OnPositiveListener onPositiveListener) {
        this.onPositiveListener = onPositiveListener;
    }
}
