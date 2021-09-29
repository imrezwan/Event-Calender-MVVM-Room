package com.rezwan2525.event_calender_mvvm_room.services.locals;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;

@Database(entities = {Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
}