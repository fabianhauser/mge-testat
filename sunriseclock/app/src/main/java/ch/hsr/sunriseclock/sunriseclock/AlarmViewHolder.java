package ch.hsr.sunriseclock.sunriseclock;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class AlarmViewHolder extends RecyclerView.ViewHolder {

    private View parent;
    private TextView textView;

    public AlarmViewHolder(View parent, TextView textView) {
        super(parent);

        this.parent = parent;
        this.textView = textView;
    }

    public TextView getTextView() {
        return this.textView;
    }

    public View getParent() {
        return this.parent;
    }
}
