package com.rezwan2525.event_calender_mvvm_room.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;
import com.rezwan2525.event_calender_mvvm_room.viewmodels.EventViewModel;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "Profile_TAG";
    FirebaseFirestore db ;
    FirebaseUser user;
    TextView mEmail;
    EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initVars();
        initWidgets();

        eventViewModel.getIsBackUpDoneState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean != null){
                    if(aBoolean){
                        backUpDoneMessage();
                    }
                }
            }
        });
    }

    private void initWidgets() {
        Log.d(TAG, "User Null: initWid");
        mEmail = findViewById(R.id.tv_email);
        if(user != null){
            Log.d(TAG, "User: "+ user.toString());
            mEmail.setText(user.getEmail());
        }




    }

    private void initVars() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        eventViewModel = new ViewModelProvider(ProfileActivity.this).get(EventViewModel.class);
    }

    public void performCloudBackup(View view) {
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                eventViewModel.backUpEventsToCloud(events);
            }
        });

    }

    @Override
    protected void onStart() {

        if(user==null){
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        }
        else{
            Log.d(TAG, "onStart: "+ user.getUid());
        }
        super.onStart();
    }

    public void signOutFromApp(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();
    }

    private void backUpDoneMessage(){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.parent_layout_of_profile_page),
                        "Cloud Backup Done", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void restoreFromCloud(View view) {
        eventViewModel.getEventsFromCloud();
    }
}