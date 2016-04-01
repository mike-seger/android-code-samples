package com.codexpedia.parcelable.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * List of Parcelables in a Parcelable Example
 * The list of parcelables: ArrayList<Student>
 *
 * The differences from simple parcelable class:
 * write: dest.writeTypedList(this.mStudents);
 * read: in.readTypedList(this.mStudents, Student.CREATOR);
 */
public class Teacher implements Parcelable {

	private String mName;
	private String mEmail;
	private int mAge;
	private ArrayList<Student> mStudents = new ArrayList<>();

	public Teacher(String sName, String sEmail, int sAge, ArrayList<Student> students) {
		this.mName = sName;
		this.mEmail = sEmail;
		this.mAge = sAge;
		this.mStudents = students;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.mName);
		dest.writeString(this.mEmail);
		dest.writeInt(this.mAge);

		dest.writeTypedList(this.mStudents);
	}

	protected Teacher(Parcel in) {
		this.mName = in.readString();
		this.mEmail = in.readString();
		this.mAge = in.readInt();

		in.readTypedList(this.mStudents, Student.CREATOR);
	}

	public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
		public Teacher createFromParcel(Parcel source) {
			return new Teacher(source);
		}
		public Teacher[] newArray(int size) {
			return new Teacher[size];
		}
	};


	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//The following are just setter and getter methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getAge() {
		return mAge;
	}

	public void setAge(int mSAge) {
		this.mAge = mSAge;
	}

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

	public ArrayList<Student> getStudents() {
		return mStudents;
	}

	public void setStudents(ArrayList<Student> mStudents) {
		this.mStudents = mStudents;
	}
}