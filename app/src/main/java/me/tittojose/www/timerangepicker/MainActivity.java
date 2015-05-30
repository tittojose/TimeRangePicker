package me.tittojose.www.timerangepicker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import me.tittojose.www.timerangepicker_library.TimeRangePickerDialog;


public class MainActivity extends ActionBarActivity implements TimeRangePickerDialog.IOnTimeRangeSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            TimeRangePickerDialog alertFragment = new TimeRangePickerDialog();
            alertFragment.show(getSupportFragmentManager(), "Ale");
        }
    }


    @Override
    public void onTimeRangeSelected(int startHour, int startMin, int endHour, int endMin) {

    }
}
