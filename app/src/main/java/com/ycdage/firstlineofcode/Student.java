package com.ycdage.firstlineofcode;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

     String name;
     int sex;
     int age;

    protected Student(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        name = in.readString();
        sex = in.readInt();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(sex);
        dest.writeInt(age);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
