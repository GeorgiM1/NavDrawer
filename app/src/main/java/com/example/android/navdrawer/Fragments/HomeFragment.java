package com.example.android.navdrawer.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.navdrawer.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pc on 1/11/2018.
 */

public class HomeFragment extends Fragment {
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_home, null);
        unbinder = ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
