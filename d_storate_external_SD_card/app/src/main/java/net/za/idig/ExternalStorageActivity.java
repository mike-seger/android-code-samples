package net.za.idig;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExternalStorageActivity extends Activity {

	String fileName = "DemoPicture.jpg";
	int pictureResourceId = R.drawable.appexperts_logo;
	ImageView myImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		myImageView = (ImageView) findViewById(R.id.imageView1);

		Button saveImageButton = (Button) findViewById(R.id.button_save_picture);
		saveImageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					savePictureToExternalStorage(getPictureInputStream(pictureResourceId));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Button displayImageButton = (Button) findViewById(R.id.button_display_image);
		displayImageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				getExternalStoragePicture();
			}
		});

		Button deleteImageButton = (Button) findViewById(R.id.button_delete_picture);
		deleteImageButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				myImageView.setImageResource(R.drawable.ic_launcher);
				deleteExternalStoragePublicPicture(fileName);
			}
		});
	}

	private int checkStateofExternalStorage() {
		int storageState = 0;// other
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			storageState = 1;// read/write
		}
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			storageState = 2;// read only
		}
		return storageState;
	}

	private InputStream getPictureInputStream(int pictureResourceId) {
		InputStream inStream = getResources()
				.openRawResource(pictureResourceId);
		return inStream;
	}

	private void getExternalStoragePicture() {
		if (checkStateofExternalStorage() != 0) {
			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			File imageFile = new File(path, fileName);
			if (imageFile.exists()) {
				Bitmap myBitmap = BitmapFactory
						.decodeFile(imageFile.toString());
				if (myBitmap != null) {
					myImageView.setImageBitmap(myBitmap);
				} else {
					myImageView.setImageResource(R.drawable.ic_launcher);
					Toast.makeText(this,
							"Sorry Bob! Can't decode the saved file",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(this,
						"Sorry Bob! The image file does not exist",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(this, "Can't read from external storage",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void savePictureToExternalStorage(InputStream inStream)
			throws IOException {
		OutputStream outStream = null;
		File createdFile = null;
		int storageState = checkStateofExternalStorage();
		if (storageState == 1) {
			File filePath = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			if (!filePath.exists()) {
				filePath.mkdirs();
			} else {
				createdFile = new File(filePath, fileName);
				if (createdFile.exists()) {
					Toast.makeText(this, "file already exists",
							Toast.LENGTH_SHORT).show();
				} else {
					try {
						outStream = new FileOutputStream(createdFile);
						byte[] data = new byte[inStream.available()];
						inStream.read(data);
						outStream.write(data);
						Toast.makeText(this, "file saved in external storage",
								Toast.LENGTH_SHORT).show();
						addFileToMediaScanner(createdFile);
					} catch (IOException e) {
						Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT)
								.show();
					} finally {
						inStream.close();
						outStream.close();
					}
				}
			}
		} else {
			Toast.makeText(this, "no write access - can't save file",
					Toast.LENGTH_SHORT).show();
		}
	}

	private void addFileToMediaScanner(File file) {
		MediaScannerConnection.scanFile(this, new String[] { file.toString() },
				null, new MediaScannerConnection.OnScanCompletedListener() {
					public void onScanCompleted(String path, Uri uri) {

					}
				});
	}

	private void deleteExternalStoragePublicPicture(String fileName) {
		if (checkStateofExternalStorage() == 1) {
			File path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			if (path.exists()) {
				File file = new File(path, fileName);
				if (file.exists()) {
					if (file.delete()) {
						Toast.makeText(this, "picture deleted",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(this, "picture not deleted",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(this,
							"the file, " + file.getName() + " does not exist!",
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(
						this,
						"the directory, " + path.getName() + " does not exist!",
						Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(this, "Can't access external storage",
					Toast.LENGTH_SHORT).show();
		}
	}
}