package com.rezwan2525.event_calender_mvvm_room.services.locals;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rezwan2525.event_calender_mvvm_room.services.models.Event;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM events_table")
    void deleteAllNotes();

    @Query("SELECT * FROM events_table")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM events_table WHERE date= :date")
    LiveData<List<Event>> getAllEventsOfParticularDate(String date);

    @Insert
    void insertAll(Event... events);
}
