// Package declaration for the app
package com.example.alumniportalapp;

// Import necessary classes
import android.content.Intent; // For switching between activities
import android.os.Bundle; // To handle activity lifecycle events
import android.view.View; // To set up and handle button clicks
import android.widget.Button; // To use Button widgets
import androidx.appcompat.app.AppCompatActivity; // Base class for activities with AppCompat support

// MainActivity class, entry point of the app
public class MainActivity extends AppCompatActivity {

    // onCreate method, called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the parent class's onCreate method
        setContentView(R.layout.activity_main); // Set the layout for this activity from activity_main.xml

        // Set up button to navigate to LoginActivity
        Button loginButton = findViewById(R.id.btn_login); // Reference the button with ID 'btn_login' in the layout
        loginButton.setOnClickListener(new View.OnClickListener() { // Set a click listener for the button
            @Override
            public void onClick(View v) { // Define what happens when the button is clicked
                // Navigate to LoginActivity
                startActivity(new Intent(MainActivity.this, LoginActivity.class)); // Start LoginActivity using an Intent
            }
        });
    }
}
