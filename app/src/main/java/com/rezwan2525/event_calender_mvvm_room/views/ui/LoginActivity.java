package com.rezwan2525.event_calender_mvvm_room.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.viewmodels.AuthViewModel;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "Login_TAG";
    EditText mEmail, mPassword;
    Button mLoginBtn;
    AuthViewModel authViewModel;
    String email, password;

    Boolean isUserLoggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidgets();

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    authViewModel.createNewAccount(email, password);
                }
            }
        });

        authViewModel.getIsUserLoggedIn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                    goToMainActivity();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validate() {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if(email != null && !email.isEmpty()){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password != null && !password.isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void initWidgets() {
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mLoginBtn = findViewById(R.id.btn_login);
    }


    public void gotoRegisterScreen(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        // User is signed in
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }
}