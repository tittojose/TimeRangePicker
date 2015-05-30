package me.tittojose.www.timerangepicker_library;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TimePicker;

/**
 * Created by Jose on 24/05/15.
 */
public class TimeRangePickerDialog extends DialogFragment implements View.OnClickListener {
    TabHost tabs;
    Button setTimeRange;
    TimePicker startTimePicker, endTimePicker;
    IOnTimeRangeSelected onTimeRangeSelectedListener;

    public interface IOnTimeRangeSelected {
        public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onTimeRangeSelectedListener = (IOnTimeRangeSelected) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement IOnTimeRangeSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.timerange_picker_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tabs = (TabHost) root.findViewById(R.id.tabHost);
        setTimeRange = (Button) root.findViewById(R.id.bSetTimeRange);
        startTimePicker = (TimePicker) root.findViewById(R.id.startTimePicker);
        endTimePicker = (TimePicker) root.findViewById(R.id.endTimePicker);
        setTimeRange.setOnClickListener(this);
        tabs.findViewById(R.id.tabHost);
        tabs.setup();
        TabHost.TabSpec tabpage1 = tabs.newTabSpec("one");
        tabpage1.setContent(R.id.startTimeGroup);
        tabpage1.setIndicator("Start Time");

        TabHost.TabSpec tabpage2 = tabs.newTabSpec("two");
        tabpage2.setContent(R.id.endTimeGroup);
        tabpage2.setIndicator("End Time");

        tabs.addTab(tabpage1);
        tabs.addTab(tabpage2);


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        // safety check
        if (getDialog() == null)
            return;
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bSetTimeRange) {
            dismiss();
            int startHour = startTimePicker.getCurrentHour();
            int startMin = startTimePicker.getCurrentMinute();
            int endHour = endTimePicker.getCurrentHour();
            int endMin = endTimePicker.getCurrentMinute();
            onTimeRangeSelectedListener.onTimeRangeSelected(startHour, startMin, endHour, endMin);
        }
    }
}
