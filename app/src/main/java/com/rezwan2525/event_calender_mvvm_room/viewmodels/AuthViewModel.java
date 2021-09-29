package com.rezwan2525.event_calender_mvvm_room.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.rezwan2525.event_calender_mvvm_room.services.repositories.AuthRepo;
import com.rezwan2525.event_calender_mvvm_room.services.repositories.OnAuthRepo;

public class AuthViewModel extends AndroidViewModel implements OnAuthRepo {
    private static String TAG = "AuthVM_TAG";
    MutableLiveData<Boolean> isAccCreated = new MutableLiveData<>(),
            isUserLoggedIn = new MutableLiveData<>();
    AuthRepo authRepo;


    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepo = new AuthRepo(application, this);
    }

    public void createNewAccount(String email, String password){
        authRepo.createNewAccount(email, password);
    }

    public void loggedInUser(String email, String password){
        authRepo.userSignIn(email, password);
    }

    public MutableLiveData<Boolean> getAccountCreateState(){
        Log.d(TAG, "Get AcCREATE: "+ isAccCreated.getValue());
        return isAccCreated;
    }

    public MutableLiveData<Boolean> getIsUserLoggedIn(){
        Log.d(TAG, "Get acLogin: "+ isUserLoggedIn.getValue());
        return isUserLoggedIn;
    }

    @Override
    public void isAccountCreated(boolean val) {
        Log.d(TAG, "IsAcCreate: "+ val);
        isAccCreated.postValue(val);
    }

    @Override
    public void isUserSignedIn(boolean val) {
        Log.d(TAG, "IsAcLoggedIn: "+ val);
        isUserLoggedIn.postValue(val);
    }
}
