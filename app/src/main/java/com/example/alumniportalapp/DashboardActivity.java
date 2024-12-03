package com.example.alumniportalapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvPhone, tvDateOfBirth, tvCgpa, tvCourse, tvFatherName,
            tvGender, tvGraduationYear, tvMotherName, tvRegisterNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Reference views
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvCgpa = findViewById(R.id.tvCgpa);
        tvCourse = findViewById(R.id.tvCourse);
        tvFatherName = findViewById(R.id.tvFatherName);
        tvGender = findViewById(R.id.tvGender);
        tvGraduationYear = findViewById(R.id.tvGraduationYear);
        tvMotherName = findViewById(R.id.tvMotherName);
        tvRegisterNo = findViewById(R.id.tvRegisterNo);

        // Get user data from the Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth");
        String cgpa = getIntent().getStringExtra("cgpa");
        String course = getIntent().getStringExtra("course");
        String fatherName = getIntent().getStringExtra("fatherName");
        String gender = getIntent().getStringExtra("gender");
        String graduationYear = getIntent().getStringExtra("graduationYear");
        String motherName = getIntent().getStringExtra("motherName");
        String registerNo = getIntent().getStringExtra("registerNo");

        // Display user data on the screen
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvDateOfBirth.setText(dateOfBirth);
        tvCgpa.setText(cgpa);
        tvCourse.setText(course);
        tvFatherName.setText(fatherName);
        tvGender.setText(gender);
        tvGraduationYear.setText(graduationYear);
        tvMotherName.setText(motherName);
        tvRegisterNo.setText(registerNo);
    }
}
