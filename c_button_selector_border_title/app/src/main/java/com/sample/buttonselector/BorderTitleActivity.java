package com.sample.buttonselector;

import android.app.Activity;
import android.os.Bundle;

public class BorderTitleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.in_from_bottom_bounce, 0);
        setContentView(R.layout.activity_border_title_demo);
    }
}
