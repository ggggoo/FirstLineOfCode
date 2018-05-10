// IMyAidlInterface.aidl
package com.ycdage.firstlineofcode;

import com.ycdage.firstlineofcode.Student;

// Declare any non-default types here with import statements

interface IStudentService {
    List<Student> getStudent();
    void addStudent(in Student student);
}
