package com.rezwan2525.event_calender_mvvm_room.services.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class AuthRepo {
    private static String TAG = "AuthRepo_TAG";

    Context context;
    OnAuthRepo listener;

    FirebaseAuth mAuth;
    public AuthRepo(Application application, OnAuthRepo listener){
        this.context = application;
        this.listener = listener;
        mAuth = FirebaseAuth.getInstance();
    }

    public void createNewAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor)context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            listener.isAccountCreated(true);
                        } else {
                            listener.isAccountCreated(false);
                        }
                    }
                });
    }


    public void userSignIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            listener.isUserSignedIn(true);
                        } else {
                            listener.isUserSignedIn(false);
                        }
                    }
                });
    }
}
