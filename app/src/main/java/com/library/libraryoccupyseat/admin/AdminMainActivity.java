package com.library.libraryoccupyseat.admin;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.admin.manage.ManageFragment;
import com.library.libraryoccupyseat.admin.set.SetFragment;
import com.library.libraryoccupyseat.admin.self.SelfFragment;
import com.library.libraryoccupyseat.base.BaseFragmentActivity;

/**
 * 管理员操作系统
 */
public class AdminMainActivity extends BaseFragmentActivity implements ViewPager.OnPageChangeListener {

    //定义数组来存放Fragment界面
    private Class<?>[] fragmentArray = { SetFragment.class,ManageFragment.class, SelfFragment.class};

    //Tab选项卡的文字
    private String[] mTextviewArray;

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.back_bg,R.drawable.back_bg,R.drawable.back_bg};
    private int currentIndex = 1;
    private LayoutInflater layoutInflater;
    private FragmentTabHost fragmentTabHost;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getSelfActivity()).inflate(R.layout.activity_admin_mian,null);
        setContentView(view);
        mTextviewArray = getResources().getStringArray(R.array.admin_main_tab_name);
        initView();
    }

    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
            fragmentTabHost.addTab(tabSpec, fragmentArray[i], null);
        }

        fragmentTabHost.setCurrentTab(currentIndex);
    }

    private View getTabItemView(int i) {
        View view = layoutInflater.inflate(R.layout.view_bottom_tab_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[i]);
        TextView textview = (TextView) view.findViewById(R.id.textview);
        textview.setText(mTextviewArray[i]);
        return view;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == currentIndex) {
            return;
        }
        if (currentIndex == 1) {
            onCreate(new Bundle());
        }
        if (position == 1) {
            onCreate(new Bundle());
        }
        currentIndex = position;
        fragmentTabHost.setCurrentTab(currentIndex);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected BaseFragmentActivity getSelfActivity() {
        return this;
    }
}
