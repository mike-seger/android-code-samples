package net.za.idig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InternalStorageActivity extends Activity {

	final String fileName = "mySampleFile.txt";
	String stringToSave = null;
	TextView myTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		stringToSave = getString(R.string.hello);

		Button writeFileButton = (Button) findViewById(R.id.button_write_file);
		writeFileButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (!fileExists()) {
					try {
						writeFile(stringToSave);
					} catch (IOException e) {
						makeToast(e.getMessage());
					}
				} else {
					displayText("the file already exists");
				}
			}
		});

		Button readFileButton = (Button) findViewById(R.id.button_read_file);
		readFileButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (fileExists()) {
					try {
						String myString = readFile();
						displayText(myString);
					} catch (IOException e) {
						makeToast(e.getMessage());
					}
				} else {
					displayText("the file does not exist");
				}
			}
		});

		Button deleteFileButton = (Button) findViewById(R.id.button_delete_file);
		deleteFileButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (fileExists()) {
					deleteMyFile(fileName);
				} else {
					displayText("the file does not exist");
				}
			}
		});
	}

	protected void makeToast(String message) {
		Toast.makeText(InternalStorageActivity.this, message,
				Toast.LENGTH_SHORT).show();
	}

	private void deleteMyFile(String fileName) {
		File dir = getFilesDir();
		File file = new File(dir, fileName);
		boolean deleted = file.delete();
		if (deleted) {
			displayText("file deleted");
		} else {
			displayText("file not deleted");
		}
	}

	private void displayText(String stringName) {
		myTextView = (TextView) findViewById(R.id.textView2);
		myTextView.setTextSize(18);
		int myTextColor = getResources().getColor(R.color.yellow);
		myTextView.setTextColor(myTextColor);
		myTextView.setText(stringName);
	}

	private void writeFile(String stringToSave) throws IOException {
		FileOutputStream outStream = null;
		try {
			outStream = openFileOutput(fileName, Context.MODE_PRIVATE);
			outStream.write(stringToSave.getBytes());
			if (fileExists()) {
				displayText("file saved");
			} else {
				displayText("file not saved");
			}
		} catch (Exception e) {
			makeToast(e.getMessage());
		} finally {
			outStream.close();
		}
	}

	private boolean fileExists() {
		File dir = getFilesDir();
		File file = new File(dir, fileName);
		return file.exists();
	}

	public String readFile() throws IOException {
		FileInputStream inStream = null;
		InputStreamReader myInputStreamReader = null;
		BufferedReader myBufferedReader = null;
		String retrievedString = "";
		try {
			inStream = openFileInput(fileName);
			myInputStreamReader = new InputStreamReader(inStream);
			myBufferedReader = new BufferedReader(myInputStreamReader);
			String readLineString = myBufferedReader.readLine();
			while (readLineString != null) {
				retrievedString = retrievedString + readLineString;
				readLineString = myBufferedReader.readLine();
			}
		} catch (IOException ioe) {
			makeToast(ioe.getMessage());
		} finally {
			myBufferedReader.close();
			myInputStreamReader.close();
			inStream.close();
		}
		return retrievedString;
	}
}