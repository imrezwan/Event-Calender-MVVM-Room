package com.rezwan2525.event_calender_mvvm_room.views.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rezwan2525.event_calender_mvvm_room.R;
import com.rezwan2525.event_calender_mvvm_room.services.models.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private Context context;
    private List<Event> eventArrayList;

    public EventAdapter(Context context, List<Event> eventArrayList) {
        this.context = context;
        this.eventArrayList = eventArrayList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_cell, null);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.mEventTitle.setText(eventArrayList.get(position).eventTitle);
        holder.mEventTime.setText(eventArrayList.get(position).startTime);
    }

    @Override
    public int getItemCount() {
        return eventArrayList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        LinearLayout parentLayout;
        TextView mEventTime, mEventTitle;
        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout = itemView.findViewById(R.id.ll_event_cell_parent);
            mEventTime = itemView.findViewById(R.id.tv_event_time);
            mEventTitle = itemView.findViewById(R.id.tv_event_title);
        }
    }
}
