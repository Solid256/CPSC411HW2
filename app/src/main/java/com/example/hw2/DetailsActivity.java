package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;
import android.view.Gravity;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    public LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the correct content view.
        setContentView(R.layout.activity_details);

        // The intent that contains the student's CWID.
        Intent intent = getIntent();

        int CWID = intent.getIntExtra("CWID", 0);

        // The current student being displayed.
        Student curStudent = StudentDB.GetSingleton().GetStudentByCWID(CWID);

        rootLayout = findViewById(R.id.details_layout);

        Button button = new Button(this);
        button.setText("Back");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If clicked, exit this view back to the summary activity.
                finish();
            }
        });
        rootLayout.addView(button);

        TextView firstNameTextView = new TextView(this);
        firstNameTextView.setText("First Name: " + curStudent.GetFirstName());
        firstNameTextView.setPadding(6,2,6,2);
        rootLayout.addView(firstNameTextView);

        TextView lastNameTextView = new TextView(this);
        lastNameTextView.setText("Last Name: " + curStudent.GetLastName());
        lastNameTextView.setPadding(6,2,6,2);
        rootLayout.addView(lastNameTextView);

        TextView CWIDTextView = new TextView(this);
        CWIDTextView.setText("CWID: " + String.valueOf(CWID));
        CWIDTextView.setPadding(6,2,6,2);
        rootLayout.addView(CWIDTextView);

        // The layout parameters for the course enrollments title.
        LinearLayout.LayoutParams lpCenterTitle = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lpCenterTitle.setMargins(2, 2,2,2);
        lpCenterTitle.gravity = Gravity.CENTER_HORIZONTAL;

        TextView courseEnrollmentsTitle = new TextView(this);
        courseEnrollmentsTitle.setText("Course Enrollments");
        courseEnrollmentsTitle.setLayoutParams(lpCenterTitle);
        courseEnrollmentsTitle.setGravity(Gravity.CENTER_HORIZONTAL);
        rootLayout.addView(courseEnrollmentsTitle);

        // The list of course enrollments.
        ArrayList<CourseEnrollment> courseEnrollments = curStudent.GetCourseEnrollments();

        // The grid layout for the student course details.
        GridLayout gridDetails = new GridLayout(this);

        rootLayout.addView(gridDetails);

        int gridRows = courseEnrollments.size();
        int gridColumns = 2;

        // The specifications for the grid.
        GridLayout.Spec[] specRow = new GridLayout.Spec[gridRows];
        GridLayout.Spec[] specColumn = new GridLayout.Spec[gridColumns];

        // Compute the grid specifications.
        for(int i = 0; i < gridRows; i++)
        {
            specRow[i] = GridLayout.spec(i);
        }

        for(int i = 0; i < gridColumns; i++)
        {
            specColumn[i] = GridLayout.spec(i);
        }

        // The columns that belong to each grid cell.
        LinearLayout[][] columns = new LinearLayout[gridRows][gridColumns];

        // The current column being modified.
        LinearLayout CurColumn;

        // Create all of the linear layouts for the grid cells.
        for(int i = 0; i < gridRows; i++)
        {
            for(int j = 0; j < gridColumns; j++)
            {
                // The current grid layout parameters.
                GridLayout.LayoutParams lpGridLayout =
                        new GridLayout.LayoutParams(specRow[i], specColumn[j]);
                lpGridLayout.width = GridLayout.LayoutParams.WRAP_CONTENT;
                lpGridLayout.height = GridLayout.LayoutParams.WRAP_CONTENT;

                lpGridLayout.setGravity(Gravity.FILL_HORIZONTAL);

                lpGridLayout.setMargins(4,4,4,4);

                // Create a new linear layout for a column inside of the grid cell.
                CurColumn = new LinearLayout(this);

                columns[i][j] = CurColumn;

                CurColumn.setLayoutParams(lpGridLayout);
                CurColumn.setOrientation(LinearLayout.VERTICAL);
                CurColumn.setBackgroundColor(Color.BLACK);

                CurColumn.setGravity(Gravity.FILL_HORIZONTAL);

                gridDetails.addView(CurColumn);
            }
        }

        // The layout parameters for a left column.
        LinearLayout.LayoutParams lpColumnLeft = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lpColumnLeft.setMargins(4, 4,4,4);
        lpColumnLeft.weight = 1;
        lpColumnLeft.gravity = Gravity.START;

        for(int i = 0; i < courseEnrollments.size(); i++) {
            // Grid cell (i, 0)
            CurColumn = columns[i][0];

            // The current course enrollment.
            CourseEnrollment curCourseEnrollment = courseEnrollments.get(i);

            TextView textViewName = new TextView(this);
            textViewName.setLayoutParams(lpColumnLeft);
            textViewName.setBackgroundColor(Color.WHITE);
            textViewName.setText(curCourseEnrollment.GetCourseID());
            textViewName.setPadding(4,4,4,4);
            CurColumn.addView(textViewName);


            // Grid cell (i, 1)
            CurColumn = columns[i][1];

            TextView textViewGrade = new TextView(this);
            textViewGrade.setLayoutParams(lpColumnLeft);
            textViewGrade.setBackgroundColor(Color.WHITE);
            textViewGrade.setText(curCourseEnrollment.GetGrade());
            textViewGrade.setPadding(4,4,4,4);
            CurColumn.addView(textViewGrade);
        }
    }
}
