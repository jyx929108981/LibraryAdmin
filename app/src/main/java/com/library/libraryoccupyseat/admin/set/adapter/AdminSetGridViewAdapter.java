package com.library.libraryoccupyseat.admin.set.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

        if (position == 0){
            View view  = LayoutInflater.from(context).inflate(R.layout.frag_admin_set_add_item,null);
            CardView add_item_cardView = (CardView) view.findViewById(R.id.add_item_cardView);
            add_item_cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAddLibrary!=null){
                        mAddLibrary.onClicklistener(v,position);
                    }
                }
            });
            return view;
        }else{
            ViewHolder viewHolder;
            if (convertView ==null){
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.frag_admin_set_new_item,null);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
        }
        return convertView;
    }

    class ViewHolder{

    }

    public interface addLibrary{
        void onClicklistener(View view,int position);
    }

    private addLibrary mAddLibrary;

    public void setAddLibrary(addLibrary addLibrary) {
        mAddLibrary = addLibrary;
    }
}
