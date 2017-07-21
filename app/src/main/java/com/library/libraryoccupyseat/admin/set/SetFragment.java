package com.library.libraryoccupyseat.admin.set;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.admin.set.adapter.AdminSetGridViewAdapter;
import com.library.libraryoccupyseat.base.BaseFragment;
import com.library.libraryoccupyseat.bean.AdminSetBean;
import com.library.libraryoccupyseat.helper.ViewTitleHelper;
import com.library.libraryoccupyseat.widget.AddLibraryDialog;

import java.util.ArrayList;

/**
 * @author jyx
 * Created by weisi on 2017/7/20.
 */

public class SetFragment extends BaseFragment {

    private View view;
    private GridView frag_admin_gridView;
    private ArrayList<AdminSetBean> mList = new ArrayList<>();
    private AdminSetGridViewAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_set,null);

        initView();
        setTitleView();
        setData();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frag_admin_gridView = (GridView) view.findViewById(R.id.frag_admin_gridView);
    }

    /**
     * 设置Title栏
     */
    private void setTitleView() {
        ViewTitleHelper.setBackLayoutVisibility(view,View.INVISIBLE);
        ViewTitleHelper.setTitleText(view,"设    置");
        ViewTitleHelper.setForwardLayoutVisibility(view,View.INVISIBLE);
    }

    private void setData() {
        AdminSetBean adminSetBean = new AdminSetBean();
        mList.add(0,adminSetBean);
        adapter = new AdminSetGridViewAdapter(getSelfActivity(),mList);
        frag_admin_gridView.setAdapter(adapter);
        adapter.setAddLibrary(new AdminSetGridViewAdapter.addLibrary() {
            @Override
            public void onClicklistener(View view, int position) {
                AddLibraryDialog dialog = new AddLibraryDialog(getSelfActivity());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected Activity getSelfActivity() {
        return getActivity();
    }
}
