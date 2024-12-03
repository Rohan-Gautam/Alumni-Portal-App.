package com.example.alumniportalapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth and Firestore
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Reference views
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Login button functionality
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            // After login, fetch user data from Firestore
                            fetchUserData();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void fetchUserData() {
        String userId = auth.getCurrentUser().getUid();

        // Fetch data from Firestore using the userId (UID)
        db.collection("users").document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            // Get the user data from Firestore
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


                            // Pass user data to the next activity (DashboardActivity)
                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("email", email);
                            intent.putExtra("phone", phone);
                            intent.putExtra("dateOfBirth", dateOfBirth);
                            intent.putExtra("cgpa", cgpa);
                            intent.putExtra("course", course);
                            intent.putExtra("fatherName", fatherName);
                            intent.putExtra("gender", gender);
                            intent.putExtra("graduationYear", graduationYear);
                            intent.putExtra("motherName", motherName);
                            intent.putExtra("registerNo", registerNo);

                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "No user data found.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error fetching user data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
