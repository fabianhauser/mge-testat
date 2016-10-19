package ch.hsr.sunriseclock.sunriseclock;

import android.view.View;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;
/**
 * Created by michi on 19.10.16.
 */

public class AlarmViewHolder extends RecyclerView.ViewHolder {
    public View parent;
    public TextView textView;

    public AlarmViewHolder(View parent, TextView textView) {
        super(parent);
        this.parent = parent;
        this.textView = textView;
    }
}
