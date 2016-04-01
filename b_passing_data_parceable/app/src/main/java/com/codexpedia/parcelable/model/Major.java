package com.codexpedia.parcelable.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Major implements Parcelable {
    private String mSubject;
    private int mGradeMinimum;

    public Major(String subject, int gradeMinimum) {
        this.mSubject = subject;
        this.mGradeMinimum = gradeMinimum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mSubject);
        dest.writeInt(this.mGradeMinimum);
    }

    protected Major(Parcel in) {
        this.mSubject = in.readString();
        this.mGradeMinimum = in.readInt();
    }

    public static final Creator<Major> CREATOR = new Creator<Major>() {
        public Major createFromParcel(Parcel source) {
            return new Major(source);
        }
        public Major[] newArray(int size) {
            return new Major[size];
        }
    };


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //The following are just setter and getter methods
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getmSubject() {
        return mSubject;
    }
    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }
    public int getmGradeMinimum() {
        return mGradeMinimum;
    }
    public void setmGradeMinimum(int mGradeMinimum) {
        this.mGradeMinimum = mGradeMinimum;
    }
}
