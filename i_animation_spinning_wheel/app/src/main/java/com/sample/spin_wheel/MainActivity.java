package com.sample.spin_wheel;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by pye on 2/11/16.
 */
import android.R.interpolator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int SPIN_DURATION = 5;
    private static final float NUM_OF_ROTATIONS = 5;
    private float mfDegreePerSegment;
    private boolean mbSpinStarted = false;

    private Button mivSpin;
    private ImageView mivWheelImage;
    private ViewFlipper vfHeaderFliper;
    private LinearLayout llCongratHeader;
    private TextView tvWinningAmontLg;
    private TextView tvWinningAmount;
    private RotateAnimation wheelRotation;
    private float mAngleToRotate;

    // Coins to win
    private static final int COINS_TO_WIN[] = {
            2500,
            2000,
            3000,
            10000,
            1000,
            5000,
            9000,
            5000,
            1200,
            3000,
            6000,
            4000
    };

    // Coins won
    private int mCoinsWon;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_wheel);

        vfHeaderFliper = (ViewFlipper) findViewById(R.id.vf_header_flipper);
        llCongratHeader = (LinearLayout) findViewById(R.id.ll_wheel_header_congrat);
        tvWinningAmontLg = (TextView) findViewById(R.id.tv_winning_amount_lg);
        tvWinningAmount = (TextView) findViewById(R.id.tv_winning_amount);
        mivSpin = (Button) findViewById(R.id.wheel_btn_spin);
        mivWheelImage = (ImageView) findViewById(R.id.iv_wheel_image);

        mivWheelImage.post(new Runnable() {
            @Override
            public void run() {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mivWheelImage.getLayoutParams();
                params.width = mivWheelImage.getHeight();
                mivWheelImage.setLayoutParams(params);
            }
        });

        mfDegreePerSegment = 360f / 12;

        mivSpin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWinningAmount.setVisibility(View.GONE);
                initSpin();
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
    }



    private void initSpin() {
        if (mbSpinStarted) return;
        spin();
    }


    private void spin() {
        mAngleToRotate = 360f * NUM_OF_ROTATIONS + mfDegreePerSegment * getRandomNumberBetween(1, 12);

        wheelRotation = new RotateAnimation(0.0f, mAngleToRotate, mivWheelImage.getWidth()/2.0f, mivWheelImage.getHeight()/2.0f);

        wheelRotation.setDuration(SPIN_DURATION * 1000);

        wheelRotation.setInterpolator(this, interpolator.accelerate_decelerate);
        wheelRotation.setFillAfter(true);

        mivWheelImage.startAnimation(wheelRotation);

        wheelRotation.setAnimationListener(new MyAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (llCongratHeader.getVisibility() == View.GONE) {
                    vfHeaderFliper.setInAnimation(getApplicationContext(), R.anim.in_from_right);
                    vfHeaderFliper.setOutAnimation(getApplicationContext(), R.anim.out_to_left);
                    vfHeaderFliper.showNext();
                }

                // Print coins won!
                tvWinningAmontLg.setText("$" + Integer.toString(mCoinsWon));
                tvWinningAmount.setText("$" + Integer.toString(mCoinsWon));
                tvWinningAmount.setVisibility(View.VISIBLE);
                mbSpinStarted = false;
            }

            public void onAnimationRepeat(Animation animation) {
                animation.setInterpolator(getApplicationContext(), interpolator.accelerate_decelerate);
                animation.setRepeatCount(0);
                animation.setFillAfter(true);
            }

            public void onAnimationStart(Animation animation) {
                mbSpinStarted = true;
            }
        });


        int wonPosition = ((int)mAngleToRotate % 360) / (int)mfDegreePerSegment;

        mCoinsWon = COINS_TO_WIN[wonPosition];

        Log.i(TAG, "Position: " + wonPosition);
        Log.i(TAG, "Coins to win: " + mCoinsWon);

    }

    private static int getRandomNumberBetween(int aStart, int aEnd) {
        Random random = new Random();

        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }

        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;

        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * random.nextDouble());

        int randomNumber =  (int)(fraction + aStart);

        Log.i(TAG, "Generated : " + randomNumber);

        return randomNumber;
    }
}
