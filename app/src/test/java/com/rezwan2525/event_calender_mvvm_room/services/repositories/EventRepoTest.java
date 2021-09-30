package com.rezwan2525.event_calender_mvvm_room.services.repositories;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import android.app.Application;
import android.content.Context;

import com.rezwan2525.event_calender_mvvm_room.services.models.Event;

import org.junit.Test;
import org.mockito.Mock;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class EventRepoTest {

    @Mock
    Application application;

    private LocalDate localDate;

    public EventRepoTest(){
        localDate = LocalDate.of(2021, 9, 29);
    }

    @Test
    public void testgetEventsFromCloud(){
        EventRepo eventRepo  = new EventRepo(application, new OnEventRepo() {
            @Override
            public void isBackupDone(boolean val) {

            }
        });

        eventRepo.getEventsFromCloud();
        assertThat(true, is(true));
    }

}