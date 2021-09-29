package com.rezwan2525.event_calender_mvvm_room.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.rezwan2525.event_calender_mvvm_room.services.repositories.AuthRepo;
import com.rezwan2525.event_calender_mvvm_room.services.repositories.OnAuthRepo;

public class AuthViewModel extends AndroidViewModel implements OnAuthRepo {
    MutableLiveData<Boolean> isAccCreated, isUserLoggedIn;
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
        return isAccCreated;
    }

    public MutableLiveData<Boolean> getIsUserLoggedIn(){
        return isUserLoggedIn;
    }

    @Override
    public void isAccountCreated(boolean val) {
        isAccCreated.setValue(val);
    }

    @Override
    public void isUserSignedIn(boolean val) {
        isUserLoggedIn.setValue(val);
    }
}
