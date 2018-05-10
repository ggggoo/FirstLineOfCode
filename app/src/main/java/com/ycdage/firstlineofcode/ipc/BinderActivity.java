package com.ycdage.firstlineofcode.ipc;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.ycdage.firstlineofcode.IStudentService;
import com.ycdage.firstlineofcode.Student;

import java.util.ArrayList;
import java.util.List;

public class BinderActivity extends Activity {

    private List<Student> students = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IStudentService.Stub mBinder = new IStudentService.Stub() {
            @Override
            public List<Student> getStudent() {
                return students;
            }

            @Override
            public void addStudent(Student student) {

            }

            @Override
            public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
                return super.onTransact(code, data, reply, flags);
            }
        };
    }

}
