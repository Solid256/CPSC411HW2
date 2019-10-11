package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.student_list_lv);

        // Create all the students and their course enrollments.
        CreateStudentsAndCourseEnrollments();

        mStudentListView = findViewById(R.id.student_list_id);

        // The adapter for the student list view.
        StudentListAdapter adapter = new StudentListAdapter();

        mStudentListView.setAdapter(adapter);
    }

    // Creates all of the students and their course enrollments for the list view.
    protected void CreateStudentsAndCourseEnrollments() {
        // The students used in this first iteration of the program.
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        Student student4 = new Student();

        // The object descriptors for the students and course enrollments.
        StudentDesc studentDesc = new StudentDesc();
        CourseEnrollmentDesc courseEnrollmentDesc = new CourseEnrollmentDesc();

        // The students being sent to the student database.
        ArrayList<Student> students = new ArrayList<>();

        // Student 1.
        // Course enrollment for weight training.
        CourseEnrollment courseEnrollment1 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Weight Training";
        courseEnrollmentDesc.mGrade = "B";
        courseEnrollment1.Init(courseEnrollmentDesc);

        // Course enrollment for sneaking.
        CourseEnrollment courseEnrollment2 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Sneaking";
        courseEnrollmentDesc.mGrade = "A";
        courseEnrollment2.Init(courseEnrollmentDesc);

        studentDesc.mFirstName = "Adam";
        studentDesc.mLastName = "Jensen";
        studentDesc.mCWID = 00000001;
        studentDesc.mCourseEnrollments.add(courseEnrollment1);
        studentDesc.mCourseEnrollments.add(courseEnrollment2);

        student1.Init(studentDesc);


        // Student 2.
        studentDesc.mCourseEnrollments = new ArrayList<>();

        // Course enrollment for jumping.
        CourseEnrollment courseEnrollment3 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Jumping";
        courseEnrollmentDesc.mGrade = "A";
        courseEnrollment3.Init(courseEnrollmentDesc);

        // Course enrollment for sneaking.
        studentDesc.mFirstName = "Luigi";
        studentDesc.mLastName = "Mario";
        studentDesc.mCWID = 00000002;
        studentDesc.mCourseEnrollments.add(courseEnrollment3);

        student2.Init(studentDesc);

        // Student 3.
        studentDesc.mCourseEnrollments = new ArrayList<>();

        // Course enrollment for running.
        CourseEnrollment courseEnrollment4 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Running";
        courseEnrollmentDesc.mGrade = "A";
        courseEnrollment4.Init(courseEnrollmentDesc);

        studentDesc.mFirstName = "Sonic";
        studentDesc.mLastName = "Hedgehog";
        studentDesc.mCWID = 00000003;
        studentDesc.mCourseEnrollments.add(courseEnrollment4);

        student3.Init(studentDesc);

        // Student 4.
        studentDesc.mCourseEnrollments = new ArrayList<>();

        // Course enrollment for running.
        CourseEnrollment courseEnrollment5 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Thinking";
        courseEnrollmentDesc.mGrade = "A";
        courseEnrollment5.Init(courseEnrollmentDesc);

        // Course enrollment for running.
        CourseEnrollment courseEnrollment6 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Coding";
        courseEnrollmentDesc.mGrade = "C";
        courseEnrollment6.Init(courseEnrollmentDesc);

        // Course enrollment for running.
        CourseEnrollment courseEnrollment7 = new CourseEnrollment();
        courseEnrollmentDesc.mCourseID = "Collecting";
        courseEnrollmentDesc.mGrade = "B";
        courseEnrollment7.Init(courseEnrollmentDesc);


        studentDesc.mFirstName = "Razputin";
        studentDesc.mLastName = "Aquato";
        studentDesc.mCWID = 00000004;
        studentDesc.mCourseEnrollments.add(courseEnrollment5);
        studentDesc.mCourseEnrollments.add(courseEnrollment6);
        studentDesc.mCourseEnrollments.add(courseEnrollment7);

        student4.Init(studentDesc);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        mStudentDB.SetStudents(students);
    }

    // Variables:

    // A quick reference to the student database singleton.
    protected StudentDB mStudentDB = StudentDB.GetSingleton();

    // The student list view.
    protected ListView mStudentListView;
}
