package com.library.libraryoccupyseat.admin.set.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.library.libraryoccupyseat.bean.AdminSetBean;

/**
 * Created by weisi on 2017/7/25.
 */

public class AdminLibraryEditorAdapter extends BaseAdapter {

    private Context context;
    private AdminSetBean mAdminSetBean;

    public AdminLibraryEditorAdapter(Context context, AdminSetBean adminSetBean) {
        this.context = context;
        mAdminSetBean = adminSetBean;

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
