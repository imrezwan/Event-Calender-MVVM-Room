package com.rezwan2525.event_calender_mvvm_room.views.customs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;
import com.rezwan2525.event_calender_mvvm_room.utils.CalendarUtils;

import org.w3c.dom.Text;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class EventInfoDialog extends Dialog implements View.OnClickListener {
    private static String TAG = "EVENTINFO_TAG";

    Context context;
    EditText mEventTitle, mEventDesc;
    TextView mEventDate, mEventStart, mEventEnd;
    Button mSaveEventBtn;

    String eventDateStr , eventStartStr, eventEndStr, eventTitleStr, eventDescStr;

    public EventInfoDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        int width = (int)(context.getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(context.getResources().getDisplayMetrics().heightPixels*0.90);
        getWindow().setLayout(width, height);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.event_info_dialog, null);
        setContentView(view);
        initWidgets();

        setEventDate();

        mEventEnd.setOnClickListener(this);
        mEventStart.setOnClickListener(this);

        mSaveEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retriveTitleAndDesc();
                if(validate()){
                    IEventInfo iEventInfo = (IEventInfo) context;
                    iEventInfo.sendInsertRequest(new Event(UUID.randomUUID().toString()
                            ,eventTitleStr, eventDateStr, eventStartStr, eventEndStr, eventDescStr));
                    dismiss();
                }
            }
        });


    }

    private void retriveTitleAndDesc() {
        eventTitleStr =  mEventTitle.getText().toString();
        eventDescStr = mEventDesc.getText().toString();
    }

    private boolean validate() {
        Log.d(TAG, ""+eventDateStr+eventStartStr+ eventEndStr+eventTitleStr+"");
        if(eventDateStr==null || eventStartStr==null ||  eventEndStr==null ||  eventTitleStr==null){
            Toast.makeText(context,"Please fillup all field", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void setEventDate() {
        eventDateStr = CalendarUtils.formattedDate(CalendarUtils.selectedDate);
        mEventDate.setText(eventDateStr);
    }

    private void initWidgets() {

        mEventTitle = findViewById(R.id.et_event_title);
        mEventDesc = findViewById(R.id.et_event_desc);
        mEventDate= findViewById(R.id.tv_event_date);
        mEventStart= findViewById(R.id.tv_event_start);
        mEventEnd= findViewById(R.id.tv_event_end);
        mSaveEventBtn = findViewById(R.id.btn_save_event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_event_start:
                doStartTimeFeature();
                break;
            case R.id.tv_event_end:
                doEndTimeFeature();
                break;
        }
    }

    private void doStartTimeFeature() {
        TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                eventStartStr= getFormattedTime(hourOfDay, minute);
                mEventStart.setText(eventStartStr);
            }
        }, 10, 0, false);
        dialog.show();
    }

    private void doEndTimeFeature() {
        TimePickerDialog dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                eventEndStr= getFormattedTime(hourOfDay, minute);
                mEventEnd.setText(eventEndStr);
            }
        }, 10, 0, false);
        dialog.show();
    }


    private String getFormattedTime(int hourOfDay, int minute) {
        Calendar datetime = Calendar.getInstance();
        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
        datetime.set(Calendar.MINUTE, minute);

        Format formatter = new SimpleDateFormat("hh:mm a");
        return formatter.format(datetime.getTime());
    }
}
