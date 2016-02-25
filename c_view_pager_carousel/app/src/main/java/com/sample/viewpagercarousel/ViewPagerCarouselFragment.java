package com.sample.viewpagercarousel;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.sample.viewpagercarousel.customization.CustomViewPageTransformer;

import java.util.ArrayList;

/**
 * Created by pye on 2/24/16.
 */
public class ViewPagerCarouselFragment extends Fragment {
    public static final String IMAGE_RESOURCE_ID = "image_resource_id";

    private ImageView ivCarouselImage;
    private int imageResourceId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_pager_carousel_fragment, container, false);
        ivCarouselImage = (ImageView) v.findViewById(R.id.iv_carousel_image);
        imageResourceId = getArguments().getInt(IMAGE_RESOURCE_ID, R.drawable.car1); // default to car1 image resource
        ivCarouselImage.setImageResource(imageResourceId);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "image: " + imageResourceId, Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
