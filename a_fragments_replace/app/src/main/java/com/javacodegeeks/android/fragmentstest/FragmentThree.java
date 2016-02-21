package com.javacodegeeks.android.fragmentstest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peng on 11/2/15.
 */
public class FragmentThree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        //Inflate the layout for this fragment

        return inflater.inflate(
                R.layout.fragment_three, container, false);

    }
}