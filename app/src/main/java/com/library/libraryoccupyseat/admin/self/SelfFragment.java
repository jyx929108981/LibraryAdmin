package com.library.libraryoccupyseat.admin.self;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.library.libraryoccupyseat.R;
import com.library.libraryoccupyseat.base.BaseFragment;

/**
 * Created by weisi on 2017/7/20.
 */

public class SelfFragment extends BaseFragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_admin_self,null);
        return view;
    }
}
