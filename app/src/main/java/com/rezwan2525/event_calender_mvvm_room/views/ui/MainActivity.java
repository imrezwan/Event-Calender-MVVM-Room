package com.rezwan2525.event_calender_mvvm_room.views.ui;

import static com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils.daysInWeekArray;
import static com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils;
import com.rezwan2525.event_calender_mvvm_room.views.adapters.CalendarAdapter;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivityTAG";
    RecyclerView mCalendarRecycler;
    CalendarAdapter mCalerdarAdapter;
    TextView   mMonthYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        initVars();

        setWeekViewDates();


    }

    private void setWeekViewDates() {
        mMonthYear.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        mCalerdarAdapter = new CalendarAdapter(this,days, (position, date) -> {
            CalendarUtils.selectedDate = date;
            Log.d(TAG, "clicked: "+date);
            setWeekViewDates();
        });

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        mCalendarRecycler.setLayoutManager(layoutManager);
        mCalendarRecycler.setAdapter(mCalerdarAdapter);
    }

    private void initVars() {
        CalendarUtils.selectedDate = LocalDate.now();
    }

    private void initWidgets() {
        mCalendarRecycler = findViewById(R.id.rv_week_days);
        mMonthYear = findViewById(R.id.tv_month_year);
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


}