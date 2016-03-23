package com.sample.viewpagerautoscroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pye on 3/22/16.
 */
public class ViewPagerFragment extends Fragment {
    public static final String LUCKY_NUMBER = "lucky_number";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewpager_item, container, false);

        TextView tvLuckNum = (TextView) v.findViewById(R.id.tv_lucky_number);
        int luckyNum = getArguments().getInt(LUCKY_NUMBER, 0);
        tvLuckNum.setText(Integer.toString(luckyNum, 10));

        return v;
    }
}