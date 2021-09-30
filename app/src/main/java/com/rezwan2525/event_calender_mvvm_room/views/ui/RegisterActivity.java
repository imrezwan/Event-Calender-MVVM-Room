package com.rezwan2525.event_calender_mvvm_room.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.viewmodels.AuthViewModel;
import com.rezwan2525.event_calender_mvvm_room.views.commons.AuthCommonActivity;

public class RegisterActivity extends AuthCommonActivity {
    private static final String TAG = "Register_TAG";
    EditText mEmail, mPassword;
    Button mRegisterBtn;
    AuthViewModel authViewModel;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        initWidgets();

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                password = mPassword.getText().toString();
                if(validate(email, password)){
                    authViewModel.createNewAccount(email, password);
                }
            }
        });

        authViewModel.getAccountCreateState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean != null){
                    if(aBoolean){
                        Toast.makeText(RegisterActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Account Creation failed !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initWidgets() {
        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mRegisterBtn = findViewById(R.id.btn_register);
    }

    public void gotoLoginScreen(View view) {
        startActivity(new Intent(this, LoginActivity.class));
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