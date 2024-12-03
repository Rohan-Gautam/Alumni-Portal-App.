package com.example.alumniportalapp; // Package declaration for the app

import android.content.Intent; // Import for navigating between activities
import android.os.Bundle; // Import for saving and restoring activity state
import android.widget.Button; // Import for Button widget
import android.widget.EditText; // Import for EditText widget
import android.widget.Toast; // Import for displaying messages to the user

import androidx.appcompat.app.AppCompatActivity; // Import for compatibility support for modern features

import com.google.firebase.auth.FirebaseAuth; // Import for Firebase Authentication
import com.google.firebase.firestore.FirebaseFirestore; // Import for Firebase Firestore database

// Class representing the LoginActivity screen
public class LoginActivity extends AppCompatActivity {

    // Declaring variables for email and password input fields
    private EditText etEmail, etPassword;

    // Declaring variable for the login button
    private Button btnLogin;

    // Declaring Firebase instances for authentication and Firestore database
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate method initializes the activity
        super.onCreate(savedInstanceState); // Call to parent class's onCreate method
        setContentView(R.layout.activity_login); // Set the UI layout for this activity

        // Initialize Firebase Auth and Firestore instances
        auth = FirebaseAuth.getInstance(); // Get Firebase authentication instance
        db = FirebaseFirestore.getInstance(); // Get Firebase Firestore instance

        // Reference views from the XML layout using their IDs
        etEmail = findViewById(R.id.etEmail); // Reference for email input field
        etPassword = findViewById(R.id.etPassword); // Reference for password input field
        btnLogin = findViewById(R.id.btnLogin); // Reference for login button

        // Set a click listener on the login button to handle login attempts
        btnLogin.setOnClickListener(v -> {
            // Get the email and password entered by the user
            String email = etEmail.getText().toString().trim(); // Trimmed email input
            String password = etPassword.getText().toString().trim(); // Trimmed password input

            // Check if email or password fields are empty
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show(); // Show error message
                return; // Stop further execution if fields are empty
            }

            // Attempt to sign in with the provided email and password
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> { // Add a listener to handle the result of the login attempt
                        if (task.isSuccessful()) { // Check if login is successful
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show(); // Show success message
                            fetchUserData(); // Call method to fetch user data from Firestore
                        } else { // If login fails
                            Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Show failure message
                        }
                    });
        });
    }

    // Method to fetch user data from Firestore database
    private void fetchUserData() {
        String userId = auth.getCurrentUser().getUid(); // Get the unique user ID of the logged-in user

        // Retrieve user data from Firestore using the userId
        db.collection("users").document(userId)
                .get() // Fetch document corresponding to the userId
                .addOnCompleteListener(task -> { // Add a listener to handle the result of the data fetch
                    if (task.isSuccessful()) { // Check if data fetch is successful
                        if (task.getResult().exists()) { // Check if user data exists in Firestore
                            // Extract user details from the document
                            String name = task.getResult().getString("name");
                            String email = task.getResult().getString("email");
                            String phone = task.getResult().getString("phone");
                            String dateOfBirth = task.getResult().getString("dob");
                            String cgpa = task.getResult().getString("cgpa");
                            String course = task.getResult().getString("course");
                            String fatherName = task.getResult().getString("father-name");
                            String gender = task.getResult().getString("gender");
                            String graduationYear = task.getResult().getString("graduation-year");
                            String motherName = task.getResult().getString("mother-name");
                            String registerNo = task.getResult().getString("register-no");

                            // Pass the retrieved user details to the DashboardActivity
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class); // Create intent for DashboardActivity
                            intent.putExtra("name", name); // Add name to intent
                            intent.putExtra("email", email); // Add email to intent
                            intent.putExtra("phone", phone); // Add phone to intent
                            intent.putExtra("dateOfBirth", dateOfBirth); // Add date of birth to intent
                            intent.putExtra("cgpa", cgpa); // Add CGPA to intent
                            intent.putExtra("course", course); // Add course to intent
                            intent.putExtra("fatherName", fatherName); // Add father's name to intent
                            intent.putExtra("gender", gender); // Add gender to intent
                            intent.putExtra("graduationYear", graduationYear); // Add graduation year to intent
                            intent.putExtra("motherName", motherName); // Add mother's name to intent
                            intent.putExtra("registerNo", registerNo); // Add register number to intent

                            startActivity(intent); // Start DashboardActivity with the provided intent
                            finish(); // Close the current activity
                        } else { // If no user data is found in Firestore
                            Toast.makeText(LoginActivity.this, "No user data found.", Toast.LENGTH_SHORT).show(); // Show error message
                        }
                    } else { // If data fetch fails
                        Toast.makeText(LoginActivity.this, "Error fetching user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show(); // Show failure message
                    }
                });
    }
}

