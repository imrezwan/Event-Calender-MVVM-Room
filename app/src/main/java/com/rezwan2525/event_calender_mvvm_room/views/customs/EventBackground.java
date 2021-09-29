package com.rezwan2525.event_calender_mvvm_room.views.customs;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class EventBackground {
    private int bg, border;

    public EventBackground(int bg, int border) {
        this.bg = bg;
        this.border = border;
    }

    public int getBg() {
        return bg;
    }

    public void setBg(int bg) {
        this.bg = bg;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
}
