package com.library.libraryoccupyseat.admin.set;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseActivity;
import com.library.libraryoccupyseat.bean.AdminSetBean;
import com.library.libraryoccupyseat.helper.ViewTitleHelper;

public class AdminLibraryEditorActivity extends BaseActivity {

    private View view;
    private GridView admin_library_gridView;
    private AdminSetBean mAdminSetBean;
    @Override
    protected void initContentViews() {
        super.initContentViews();
        view = LayoutInflater.from(getSelfActivity()).inflate(R.layout.activity_admin_library_editor,null);
        setContentView(view);

        {
            Intent intent = getIntent();
            mAdminSetBean = (AdminSetBean) intent.getSerializableExtra("libraryInfo");
        }

        initView();
        setTitleView();

    }

    private void initView() {
        admin_library_gridView = (GridView) view.findViewById(R.id.admin_library_gridView);
    }

    private void setTitleView() {
        ViewTitleHelper.setBackLayoutVisibility(view,View.VISIBLE);
        ViewTitleHelper.setBackLayoutClickListtener(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelfActivity().finish();
            }
        });

        ViewTitleHelper.setForwardLayoutVisibility(view,View.INVISIBLE);
        ViewTitleHelper.setTitleText(view,mAdminSetBean.getLibraryName());
    }

    @Override
    protected BaseActivity getSelfActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGridViewData();
    }

    private void setGridViewData() {

    }
}
