package com.example.peng.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peng on 11/2/15.
 */
public class FragmentA extends Fragment {
    public FragmentA(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);

        return inflater.inflate(R.layout.fragment_a, container, false);
    }
}