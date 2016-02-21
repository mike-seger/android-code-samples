package com.codexpedia.files_storage;

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
import android.widget.EditText;
import android.widget.TextView;

public class FileStorageActivity extends Activity {
	private static final String fileName = "my_note.txt";
	private EditText etInputString;
	private Button btnWrite;
	private Button btnRead;
	private Button btnDelete;
	private TextView tvDisplay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		getApplicationContext();
		etInputString = (EditText) findViewById(R.id.et_input_string);
		btnWrite = (Button) findViewById(R.id.btn_write);
		btnRead = (Button) findViewById(R.id.btn_read);
		btnDelete = (Button) findViewById(R.id.btn_delete);
		tvDisplay = (TextView) findViewById(R.id.tv_display);

		initButtonListeners();
	}

	private void initButtonListeners() {
		btnWrite.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (!fileExists()) {
					try {
						String inputString = etInputString.getText().toString();
						writeFile(inputString);
					} catch (IOException e) {
						errorNotification(e.getMessage());
					}
				} else {
					displayText(fileName + " already exists!");
				}
			}
		});

		btnRead.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (fileExists()) {
					try {
						String myString = readFile();
						displayText(myString);
					} catch (IOException e) {
						errorNotification(e.getMessage());
					}
				} else {
					displayText(fileName + " does not exist!");
				}
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (fileExists()) {
					deleteMyFile(fileName);
				} else {
					displayText(fileName + " does not exist");
				}
			}
		});
	}

	private void writeFile(String stringToSave) throws IOException {
		FileOutputStream outStream = null;
		try {
			outStream = openFileOutput(fileName, Context.MODE_PRIVATE);
			outStream.write(stringToSave.getBytes());
			if (fileExists()) {
				displayText("File saved.");
			} else {
				displayText("File not saved.");
			}
		} catch (Exception e) {
			errorNotification(e.getMessage());
		} finally {
			outStream.close();
		}
	}

	private String readFile() throws IOException {
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
			errorNotification(ioe.getMessage());
		} finally {
			myBufferedReader.close();
			myInputStreamReader.close();
			inStream.close();
		}
		return retrievedString;
	}

	private void deleteMyFile(String fileName) {
		File dir = getFilesDir();
		File file = new File(dir, fileName);
		boolean deleted = file.delete();
		if (deleted) {
			displayText("File deleted.");
		} else {
			displayText("File not deleted.");
		}
	}

	private boolean fileExists() {
		File dir = getFilesDir();
		File file = new File(dir, fileName);
		return file.exists();
	}

	private void displayText(String stringName) {
		tvDisplay = (TextView) findViewById(R.id.tv_display);
		tvDisplay.setText(stringName);
	}

	private void errorNotification(String message) {
		tvDisplay = (TextView) findViewById(R.id.tv_display);
		tvDisplay.setTextColor(getResources().getColor(R.color.red));
		tvDisplay.setText("ERROR: " + message);
	}
}