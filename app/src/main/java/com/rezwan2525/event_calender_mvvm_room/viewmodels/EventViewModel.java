package com.rezwan2525.event_calender_mvvm_room.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rezwan2525.event_calender_mvvm_room.services.models.Event;
import com.rezwan2525.event_calender_mvvm_room.services.repositories.EventRepo;

import java.util.List;

public class EventViewModel extends AndroidViewModel {
    EventRepo eventRepo;
    LiveData<List<Event>> particularDateEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);
        eventRepo = new EventRepo(application);

    }

    public LiveData<List<Event>> getParticularDateEvents(String date){
        return eventRepo.getParticularDateEvents(date);
    }

    public void insertEvent(Event event){
        eventRepo.insertOneEvent(event);
    }




}
