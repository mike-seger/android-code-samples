package in.wptrafficanalyzer.parcelobjectdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
	
	String mSName;
	String mSEmail;
	int mSAge;
	String mSAddress;
	String mSCourse;

	/*
	 * A constructor that initializes the Student object
	 */
	public Student(String sName, String sEmail,  int sAge, String sAddress, String sCourse){
		this.mSName = sName;
		this.mSEmail = sEmail;
		this.mSAge = sAge;
		this.mSAddress = sAddress;
		this.mSCourse = sCourse;		
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mSName);
		dest.writeString(this.mSEmail);
		dest.writeInt(this.mSAge);
		dest.writeString(this.mSAddress);
		dest.writeString(this.mSCourse);
	}

	protected Student(Parcel in) {
		this.mSName = in.readString();
		this.mSEmail = in.readString();
		this.mSAge = in.readInt();
		this.mSAddress = in.readString();
		this.mSCourse = in.readString();
	}

	public static final Creator<Student> CREATOR = new Creator<Student>() {
		public Student createFromParcel(Parcel source) {
	return new Student(source);
		}

		public Student[] newArray(int size) {
			return new Student[size];
		}
	};
}