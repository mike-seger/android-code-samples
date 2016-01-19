package com.codexpedia.memoryleaksdemo.samples;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.codexpedia.memoryleaksdemo.R;

import java.lang.ref.WeakReference;
import java.util.Date;

/**
 * Created by peng on 1/17/16.
 */
public class AsyncTaskLeak extends AppCompatActivity {

    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_samples);
        tvText = (TextView) findViewById(R.id.tv_sample);

        new SampleTask(this).execute();
    }

    public void updateText(String text) {
        tvText.setText(text);
    }

    private static class SampleTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<AsyncTaskLeak> mRef;

        public SampleTask(AsyncTaskLeak activity) {
            mRef = new WeakReference<AsyncTaskLeak>(activity);
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            AsyncTaskLeak asyncTaskLeak = mRef.get();
            if (asyncTaskLeak != null)
                asyncTaskLeak.updateText("Done " + new Date().getTime());
        }

    }
}
