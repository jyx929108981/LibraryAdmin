/**
 * 
 */
package com.library.libraryoccupyseat.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.utils.JyxScreenUtils;


public class ShowProgress
{
	private String TAG = "<<<<<<<<<<ShowProgress>>>>>>>>>>";

    public static Dialog cancelDialog;
    private static ShowProgress dialogUtils;

    public static ShowProgress getInstance(){
    	if(null== dialogUtils){
    		dialogUtils = new ShowProgress();
    	}
		return dialogUtils;
    }
    
    /**
     * Activity加载动画
     * 
     * @param context
     * @param msg
     */
    public void showActivityAnimation(Context context, String msg)
    {
    	 //显示器，先判断，当前界面上是否有正在显示的，如果有的话，直接取消，然后再创建下一个
    	 dismiss();
        if (context == null){
            return;
        }
    	cancelDialog = new AlertDialog.Builder(context).create();
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_progress_dialog_anim, null);

        TextView tvInfo = (TextView) view.findViewById(R.id.tv_info);

        if (TextUtils.isEmpty(msg)){
            msg = "加载中...";
        }
        tvInfo.setText(msg);
        cancelDialog.show();
        cancelDialog.setContentView(view);
        Window window = cancelDialog.getWindow() ;
        WindowManager.LayoutParams params = window.getAttributes() ;
        Point displaySize = JyxScreenUtils.getDisplaySize(context);
        params.width =displaySize.x/3*2; //使用这种方式更改了dialog的框宽
        params.gravity = Gravity.CENTER;
        params.alpha = 1;
        window.setAttributes(params);

    }
    
    /**
     * 点击空白区域，是否消失
     * @param isCancel
     */
    public void setCanceledOnTouchOutsid(boolean isCancel){
    	cancelDialog.setCanceledOnTouchOutside(isCancel);
    }

    /**
     * Activity加载动画消失
     */
    public void dismiss()
    {
        if (null != cancelDialog)
        {
        	//cancelDialog.setCanceledOnTouchOutside(true);
        	 if (cancelDialog.isShowing()) {
                 cancelDialog.dismiss();
             }
        }
    }
}
