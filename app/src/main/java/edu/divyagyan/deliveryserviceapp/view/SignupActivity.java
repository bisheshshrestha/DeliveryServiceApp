package edu.divyagyan.deliveryserviceapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import edu.divyagyan.deliveryserviceapp.R;
import edu.divyagyan.deliveryserviceapp.viewmodel.SignupViewModel;

public class SignupActivity extends AppCompatActivity {

    private SignupViewModel signupViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText usernameEditText = findViewById(R.id.usernameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);
        EditText addressEditText = findViewById(R.id.addressEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);
        Button signupButton = findViewById(R.id.signupButton);
        TextView loginRedirectTextView = findViewById(R.id.loginRedirectTextView);

        signupViewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        signupViewModel.getSignupStatus().observe(this, status -> {
            Toast.makeText(SignupActivity.this, status, Toast.LENGTH_SHORT).show();
            if (status.equals("Sign Up Successful")) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        signupButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            signupViewModel.signUpUser(name, email, username, password, address, phone);
        });

        loginRedirectTextView.setOnClickListener(v -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }
}
