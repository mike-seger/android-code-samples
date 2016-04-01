package com.codexpedia.parcelable.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Nested Parcelable Example
 * The nested parcelable object: Major
 *
 * The differences from simple parcelable class:
 * The parcelable object has to be the first one in the read and write
 *
 * write: dest.writeParcelable(this.mMajor, flags);
 * read: this.mMajor = in.readParcelable(Major.class.getClassLoader());
 */
public class Student implements Parcelable {
	
	private String mName;
	private String mEmail;
	private int mAge;
	private Major mMajor;

	public Student(String sName, String sEmail, int sAge, Major major) {
		this.mName = sName;
		this.mEmail = sEmail;
		this.mAge = sAge;
		this.mMajor = major;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		//The parcelable object has to be the first one
		dest.writeParcelable(this.mMajor, flags);
		dest.writeString(this.mName);
		dest.writeString(this.mEmail);
		dest.writeInt(this.mAge);
	}

	protected Student(Parcel in) {
		// The order of the properties has to be the same in the writeToParcel method
		this.mMajor = in.readParcelable(Major.class.getClassLoader());
		this.mName = in.readString();
		this.mEmail = in.readString();
		this.mAge = in.readInt();
	}

	public static final Creator<Student> CREATOR = new Creator<Student>() {
		public Student createFromParcel(Parcel source) {
			return new Student(source);
		}
		public Student[] newArray(int size) {
			return new Student[size];
		}
	};


	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//The following are just setter and getter methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getEmail() {
		return mEmail;
	}
	public void setEmail(String mSEmail) {
		this.mEmail = mSEmail;
	}
	public String getName() {
		return mName;
	}
	public void setName(String mSName) {
		this.mName = mSName;
	}
	public int getAge() {
		return mAge;
	}
	public void setAge(int mSAge) {
		this.mAge = mSAge;
	}
	public Major getmMajor() {
		return mMajor;
	}
	public void setmMajor(Major mMajor) {
		this.mMajor = mMajor;
	}
}