package com.library.libraryoccupyseat.helper;

import android.view.View;
import android.widget.TextView;

import com.library.libraryoccupyseat.R;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class ViewTitleHelper {

    //设置title
    public static void setTitleText(View view, String text) {
        ((TextView) view.findViewById(R.id.title_text)).setText(text);
    }


    //设置返回键是否显示
    public static void setBackLayoutVisibility(View view, int visibility) {
        view.findViewById(R.id.back_layout).setVisibility(visibility);
    }

    //设置返回键是否显示
    public static void setBackText(View view, String backTxt) {
        ((TextView) view.findViewById(R.id.back_txt)).setText(backTxt);
    }

    //设置右边按钮是否可见
    public static void setForwardLayoutVisibility(View view, int visibility) {
        view.findViewById(R.id.forward_layout).setVisibility(visibility);
    }

    //设置右边按钮的背景
    public static void setForwardLayoutResource(View view, int drawable) {
        view.findViewById(R.id.forward_button).setBackgroundResource(drawable);
    }

    //设置右边按钮的文字显示
    public static void setForwardLayoutText(View view, String text) {
        TextView textview = (TextView) view.findViewById(R.id.forward_button);
        textview.setText(text);
    }

    public static void setForwardLayoutTextColor(View view, int color) {
        TextView textview = (TextView) view.findViewById(R.id.forward_button);
        textview.setTextColor(color);
    }


    public static void setBackLayoutClickListtener(View view, View.OnClickListener listener) {
        view.findViewById(R.id.back_layout).setOnClickListener(listener);
    }

    //设置右边按钮的点击监听
    public static void setForwardClickListener(View view, View.OnClickListener listener) {
        view.findViewById(R.id.forward_layout).setOnClickListener(listener);
    }

}
