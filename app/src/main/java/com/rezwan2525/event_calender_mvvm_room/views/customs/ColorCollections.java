package com.rezwan2525.event_calender_mvvm_room.views.customs;

import android.content.Context;

import com.rezwan2525.event_calender_mvvm_room.R;

public class ColorCollections {

    private Context context;
    public static EventBackground[] bgList;


    public ColorCollections(Context context){
        this.context = context;
        bgList = new EventBackground[]{
                new EventBackground(context.getColor(R.color.red_200), context.getColor(R.color.red_500)),
                new EventBackground(context.getColor(R.color.green_200), context.getColor(R.color.green_500)),
                new EventBackground(context.getColor(R.color.orange_200), context.getColor(R.color.orange_500))
        };
    }




}
