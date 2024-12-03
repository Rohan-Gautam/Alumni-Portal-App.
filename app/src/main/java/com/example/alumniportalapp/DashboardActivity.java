// Package declaration for the app
package com.example.alumniportalapp;

// Import necessary classes
import android.os.Bundle; // To handle activity lifecycle events
import android.widget.TextView; // To display text on the screen
import androidx.appcompat.app.AppCompatActivity; // Base class for activities with AppCompat support

// DashboardActivity class to display user information
public class DashboardActivity extends AppCompatActivity {

    // Declare TextView variables for displaying user data
    private TextView tvName, tvEmail, tvPhone, tvDateOfBirth, tvCgpa, tvCourse, tvFatherName,
            tvGender, tvGraduationYear, tvMotherName, tvRegisterNo;

    // onCreate method, called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the parent class's onCreate method
        setContentView(R.layout.activity_dashboard); // Set the layout for this activity from activity_dashboard.xml

        // Reference views by their IDs in the layout
        tvName = findViewById(R.id.tvName); // Link the TextView for the name
        tvEmail = findViewById(R.id.tvEmail); // Link the TextView for the email
        tvPhone = findViewById(R.id.tvPhone); // Link the TextView for the phone number
        tvDateOfBirth = findViewById(R.id.tvDateOfBirth); // Link the TextView for the date of birth
        tvCgpa = findViewById(R.id.tvCgpa); // Link the TextView for the CGPA
        tvCourse = findViewById(R.id.tvCourse); // Link the TextView for the course
        tvFatherName = findViewById(R.id.tvFatherName); // Link the TextView for the father's name
        tvGender = findViewById(R.id.tvGender); // Link the TextView for the gender
        tvGraduationYear = findViewById(R.id.tvGraduationYear); // Link the TextView for the graduation year
        tvMotherName = findViewById(R.id.tvMotherName); // Link the TextView for the mother's name
        tvRegisterNo = findViewById(R.id.tvRegisterNo); // Link the TextView for the register number

        // Retrieve user data passed from the previous activity through Intent
        String name = getIntent().getStringExtra("name"); // Get the user's name
        String email = getIntent().getStringExtra("email"); // Get the user's email
        String phone = getIntent().getStringExtra("phone"); // Get the user's phone number
        String dateOfBirth = getIntent().getStringExtra("dateOfBirth"); // Get the user's date of birth
        String cgpa = getIntent().getStringExtra("cgpa"); // Get the user's CGPA
        String course = getIntent().getStringExtra("course"); // Get the user's course
        String fatherName = getIntent().getStringExtra("fatherName"); // Get the user's father's name
        String gender = getIntent().getStringExtra("gender"); // Get the user's gender
        String graduationYear = getIntent().getStringExtra("graduationYear"); // Get the user's graduation year
        String motherName = getIntent().getStringExtra("motherName"); // Get the user's mother's name
        String registerNo = getIntent().getStringExtra("registerNo"); // Get the user's register number

        // Set the retrieved data to the corresponding TextViews to display on the screen
        tvName.setText(name); // Display the user's name
        tvEmail.setText(email); // Display the user's email
        tvPhone.setText(phone); // Display the user's phone number
        tvDateOfBirth.setText(dateOfBirth); // Display the user's date of birth
        tvCgpa.setText(cgpa); // Display the user's CGPA
        tvCourse.setText(course); // Display the user's course
        tvFatherName.setText(fatherName); // Display the user's father's name
        tvGender.setText(gender); // Display the user's gender
        tvGraduationYear.setText(graduationYear); // Display the user's graduation year
        tvMotherName.setText(motherName); // Display the user's mother's name
        tvRegisterNo.setText(registerNo); // Display the user's register number
    }
}
