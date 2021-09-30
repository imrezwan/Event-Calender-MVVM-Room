package com.rezwan2525.event_calender_mvvm_room.views.commons;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rezwan2525.event_calender_mvvm_room.viewmodels.AuthViewModel;

public class AuthCommonActivity extends AppCompatActivity {


    public boolean validate(String email, String password) {

        if(email == null || email.isEmpty()){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return false;
        }

        else if(password == null || password.isEmpty()){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(password.length() < 6){
            Toast.makeText(this, "Password must be greater than 6 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
