package com.download.androidfileprogressdownload;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	// button to show progress dialog
	Button btnShowProgress;
	// Progress Dialog

	ProgressDialog barProgressDialog;
	ImageView my_image;
		
	// File url to download
	private static String file_url = "http://api.androidhive.info/progressdialog/hive.jpg";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// show progress bar button
		btnShowProgress = (Button) findViewById(R.id.btnProgressBar);
		// Image view to show image after downloading
		my_image = (ImageView) findViewById(R.id.my_image);
		/**
		 * Show Progress bar click event
		 * */
		btnShowProgress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// starting new Async Task
				
				barProgressDialog = new ProgressDialog(MainActivity.this);
				
				//barProgressDialog.setTitle("Downloading Image ...");
				//barProgressDialog.setMessage("Download in progress ...");
				barProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				barProgressDialog.setProgress(0);
				barProgressDialog.setMax(20);
				
				new DownloadFileFromURL().execute(file_url);
			}
		});
	}


	/**
	 * Background Async Task to download file
	 * 
	 **/
	class DownloadFileFromURL extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread
		 * Show Progress Bar Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			barProgressDialog.show();
		}

		/**
		 * Downloading file in background thread
		 * */
		@Override
		protected String doInBackground(String... f_url) {
			int count;
	        try {
	            URL url = new URL(f_url[0]);
	            URLConnection conection = url.openConnection();
	            conection.connect();
	            // getting file length
	            int lenghtOfFile = conection.getContentLength();

	            // input stream to read file - with 8k buffer
	            InputStream input = new BufferedInputStream(url.openStream(), 8192);
	            
	            // Output stream to write file
	            OutputStream output = new FileOutputStream("/sdcard/downloadedfile.jpg");

	            byte data[] = new byte[1024];

	            long total = 0;

	            while ((count = input.read(data)) != -1) {
	                total += count;
	                // publishing the progress....
	                // After this onProgressUpdate will be called
	                
	                publishProgress(""+(int)((total*100)/lenghtOfFile));
	                
	                // writing data to file
	                output.write(data, 0, count);
	            }

	            // flushing output
	            output.flush();
	            
	            // closing streams
	            output.close();
	            input.close();
	            
	        } catch (Exception e) {
	        	Log.e("Error: ", e.getMessage());
	        }
	        
	        return "downloadedfile.jpg";
		}
		
		/**
		 * Updating progress bar
		 **/
		protected void onProgressUpdate(String... progress) {
			// setting progress percentage
			barProgressDialog.setProgress(Integer.parseInt(progress[0]));
       }

		/**
		 * After completing background task
		 * Dismiss the progress dialog
		 * **/
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after the file was downloaded
			barProgressDialog.dismiss();
			// Displaying downloaded image into image view
			// Reading image path from sdcard
			String imagePath = Environment.getExternalStorageDirectory().toString() + "/" + file_url;
			// setting downloaded into image view
			my_image.setImageDrawable(Drawable.createFromPath(imagePath));
		}

	}
}