package com.library.libraryoccupyseat.admin.set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.admin.set.adapter.AdminSetGridViewAdapter;
import com.library.libraryoccupyseat.base.BaseFragment;
import com.library.libraryoccupyseat.bean.AdminSetBean;
import com.library.libraryoccupyseat.helper.ViewTitleHelper;
import com.library.libraryoccupyseat.widget.AddLibraryDialog;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * @author jyx
 *         Created by weisi on 2017/7/20.
 */

public class SetFragment extends BaseFragment {

    private View view;
    private GridView frag_admin_gridView;
    private ArrayList<AdminSetBean> mList = new ArrayList<>();
    private AdminSetGridViewAdapter adapter;
    private FloatingActionButton floating_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_set, null);

        initView();
        setTitleView();
        setGridOnItemListener();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        frag_admin_gridView = (GridView) view.findViewById(R.id.frag_admin_gridView);
        floating_btn = (FloatingActionButton) view.findViewById(R.id.floating_btn);
        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AddLibraryDialog dialog = new AddLibraryDialog(getSelfActivity());
                dialog.setOnCancelListener(new AddLibraryDialog.OnCancelListener() {
                    @Override
                    public void ClickListener(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.setOnPositiveListener(new AddLibraryDialog.OnPositiveListener() {
                    @Override
                    public void ClickListener(View view) {
                        final AdminSetBean libraryInfo = dialog.getLibraryInfo();
                        libraryInfo.save(new SaveListener<String>() {

                            @Override
                            public void done(String objectId, BmobException e) {
                                if (e == null) {
                                    mList.add(libraryInfo);
                                    if (adapter == null) {
                                        adapter = new AdminSetGridViewAdapter(getSelfActivity(), mList);
                                        frag_admin_gridView.setAdapter(adapter);
                                    } else {
                                        adapter.notifyDataSetChanged();
                                    }
                                    Toast.makeText(getSelfActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * 设置Title栏
     */
    private void setTitleView() {
        ViewTitleHelper.setBackLayoutVisibility(view, View.INVISIBLE);
        ViewTitleHelper.setTitleText(view, "设    置");
        ViewTitleHelper.setForwardLayoutVisibility(view, View.INVISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();

        {
            getLibraryList();
        }
    }

    private void getLibraryList() {
        BmobQuery<AdminSetBean> query = new BmobQuery<AdminSetBean>();
        //查询playerName叫“比目”的数据
        query.addWhereEqualTo("user", 0);
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<AdminSetBean>() {
            @Override
            public void done(List<AdminSetBean> object, BmobException e) {
                if (e == null) {
                    mList.addAll(object);
                    if (adapter == null) {
                        adapter = new AdminSetGridViewAdapter(getSelfActivity(), mList);
                        frag_admin_gridView.setAdapter(adapter);
                    } else {
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    private void setGridOnItemListener() {
        if (adapter!= null){
            //单击查看编辑
            frag_admin_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AdminSetBean adminSetBean = mList.get(position);
                    Intent intent = new Intent(getSelfActivity(),AdminLibraryEditorActivity.class);
                    intent.putExtra("libraryInfo",adminSetBean);
                    getSelfActivity().startActivity(intent);
                }
            });

            //长按，操作菜单
            frag_admin_gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return false;
                }
            });
        }
    }

    @Override
    protected Activity getSelfActivity() {
        return getActivity();
    }
}
