package com.rezwan2525.event_calender_mvvm_room.services.repositories;

import android.app.Application;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rezwan2525.event_calender_mvvm_room.services.locals.AppDatabase;
import com.rezwan2525.event_calender_mvvm_room.services.locals.AppDatabaseInstance;
import com.rezwan2525.event_calender_mvvm_room.services.locals.EventDao;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class EventRepo{

    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;

    public EventRepo(Application application) { //application is subclass of context
        AppDatabase database = AppDatabaseInstance.getAppDatabaseInstance(application);
        eventDao = database.eventDao();
    }

    public void insertOneEvent(Event event){
        new Thread(new Runnable() {
            @Override
            public void run() {
                eventDao.insert(event);
            }
        }).start();
    }


    public LiveData<List<Event>> getParticularDateEvents(String date){
        return eventDao.getAllEventsOfParticularDate(date);
    }




}
