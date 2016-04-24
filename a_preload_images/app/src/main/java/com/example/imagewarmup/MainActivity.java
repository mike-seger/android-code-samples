package com.example.imagewarmup;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private LinearLayout llImageContainer;
    private String [] imgUrls= {
            "http://developer.android.com/images/activity_lifecycle.png",
            "http://www.50img.com/wp-content/uploads/2016/04/534b3ae08bbf4.image_.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/Art2.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day-2.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/Earth_Day.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day-1.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/Earth-Day-2.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/EarthDay.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earthday_pic.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earthday11.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day-2013-istock23733018.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day-clip-art-Meg_Earthday_Clipart_06.png",
            "http://www.50img.com/wp-content/uploads/2016/04/Earth-Day-hands.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/Earth-Day-Logo.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/earth-day-nail.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/Earth-Day-Pictures-Gallery-3.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/eday5.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/happy_earth_day_2013__by_dragofyre-d62levm.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/image.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/oak051856.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/sacramentoearthday_dana-gray.jpg",
            "http://www.50img.com/wp-content/uploads/2016/04/ShowImage.jpg",
            "http://www.50img.com/wp-content/uploads/2016/03/HappyEaster2015-60x57.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llImageContainer = (LinearLayout) findViewById(R.id.ll_image_container);

        preloadImages();
    }

    private void preloadImages() {
        for(String url: imgUrls) {
            Picasso.with(getApplicationContext()).load(url).fetch();
        }
    }

    public void loadImages(View v) {
        for(final String url: imgUrls) {
            final ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llImageContainer.addView(imageView);

            Picasso.with(getApplicationContext()).load(url).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d("Picasso", "Image loaded from cache>>>" + url);
                }

                @Override
                public void onError() {
                    Log.d("Picasso", "Try again in ONLINE mode if load from cache is failed");
                    Picasso.with(getApplicationContext()).load(url).into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("Picasso", "Image loaded from web>>>" + url);
                        }

                        @Override
                        public void onError() {
                            Log.d("Picasso", "Failed to load image online and offline, make sure you enabled INTERNET permission for your app and the url is correct>>>>>>>" + url);
                        }
                    });
                }
            });
        }
    }

    private void loadWithLogging() {
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        exception.printStackTrace();
                    }
                }).build();

        picasso.load("http://developer.android.com/images/activity_lifecycle.png").into(new ImageView(this));
    }

}
