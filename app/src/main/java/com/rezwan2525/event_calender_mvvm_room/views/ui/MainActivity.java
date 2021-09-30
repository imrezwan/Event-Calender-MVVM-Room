package com.rezwan2525.event_calender_mvvm_room.views.ui;

import static com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils.daysInWeekArray;
import static com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils.formattedDate;
import static com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;
import com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils;
import com.rezwan2525.event_calender_mvvm_room.viewmodels.EventViewModel;
import com.rezwan2525.event_calender_mvvm_room.views.adapters.CalendarAdapter;
import com.rezwan2525.event_calender_mvvm_room.views.adapters.EventAdapter;
import com.rezwan2525.event_calender_mvvm_room.views.customs.EventInfoDialog;
import com.rezwan2525.event_calender_mvvm_room.views.customs.IEventInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements IEventInfo {
    private final static String TAG = "MainActivityTAG";
    RecyclerView mCalendarRecycler, mEventRecycler;
    CalendarAdapter mCalerdarAdapter;
    TextView   mMonthYear, mEventDate;

    EventViewModel eventViewModel;
    EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initVars();

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        setWeekViewDates();
        setEventViewForEachDate(CalendarUtils.selectedDate);
    }

    private void setWeekViewDates() {
        mMonthYear.setText(monthYearFromDate(CalendarUtils.selectedDate));
        mEventDate.setText("Events ("+formattedDate(CalendarUtils.selectedDate)+")");

        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);



        mCalerdarAdapter = new CalendarAdapter(this,days, (position, date) -> {
            CalendarUtils.selectedDate = date;
            //Log.d(TAG, "clicked: "+ date);
            setWeekViewDates();
            setEventViewForEachDate(date);

        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        mCalendarRecycler.setLayoutManager(layoutManager);
        mCalendarRecycler.setAdapter(mCalerdarAdapter);
    }

    private void setEventViewForEachDate(LocalDate date) {
        eventViewModel.getParticularDateEvents(CalendarUtils.formattedDate(date)).observe(this, events -> {
            eventAdapter = new EventAdapter(MainActivity.this, events);
            //Log.d(TAG, events.toString());
            mEventRecycler.setAdapter(eventAdapter);
        });
    }

    private void initVars() {
        CalendarUtils.selectedDate = LocalDate.now();
        eventViewModel = new ViewModelProvider(MainActivity.this ).get(EventViewModel.class);

        //event recycler
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        mEventRecycler.setLayoutManager(layoutManager);
    }

    private void initWidgets() {
        mCalendarRecycler = findViewById(R.id.rv_week_days);
        mMonthYear = findViewById(R.id.tv_month_year);
        mEventRecycler = findViewById(R.id.rv_events_list);
        mEventDate = findViewById(R.id.tv_events_date);

    }

    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekViewDates();
    }

    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekViewDates();
    }


    public void openEventInformationDialog(View view) {
        EventInfoDialog dialog = new EventInfoDialog(this);
        dialog.show();

    }

    @Override
    public void sendInsertRequest(Event event) {
        Log.d(TAG, "DATA: "+ event.toString());
        eventViewModel.insertEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Log.d(TAG, "==> "+item.getItemId());
        if (item.getItemId()== R.id.my_account) {
            goToProfilePage();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }


    }


    private void goToProfilePage() {
        startActivity(new Intent(this, ProfileActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}