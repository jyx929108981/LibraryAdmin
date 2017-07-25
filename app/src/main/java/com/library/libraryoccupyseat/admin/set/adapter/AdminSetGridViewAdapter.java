package com.library.libraryoccupyseat.admin.set.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.bean.AdminSetBean;

import java.util.ArrayList;

/**
 * Created by weisi on 2017/7/21.
 */

public class AdminSetGridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AdminSetBean> mList = new ArrayList<>();

    public AdminSetGridViewAdapter(Context context, ArrayList<AdminSetBean> list) {
        this.context = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView ==null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.frag_admin_set_new_item,null);
                viewHolder.item_library_name = (TextView) convertView.findViewById(R.id.item_library_name);
                viewHolder.item_library_floor = (TextView) convertView.findViewById(R.id.item_library_floor);
                viewHolder.item_library_width = (TextView) convertView.findViewById(R.id.item_library_width);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            AdminSetBean adminSetBean = mList.get(position);
            if (adminSetBean!= null){
                viewHolder.item_library_name.setText(adminSetBean.getLibraryName());
                viewHolder.item_library_floor.setText("楼层:"+adminSetBean.getLibraryFloor());
                viewHolder.item_library_width.setText("宽度:"+adminSetBean.getLibraryWidth());
            }
            return convertView;

    }

    class ViewHolder{
        private TextView item_library_name;
        private TextView item_library_floor;
        private TextView item_library_width;
    }

    public interface addLibrary{
        void onClicklistener(View view,int position);
    }

    private addLibrary mAddLibrary;

    public void setAddLibrary(addLibrary addLibrary) {
        mAddLibrary = addLibrary;
    }
}
