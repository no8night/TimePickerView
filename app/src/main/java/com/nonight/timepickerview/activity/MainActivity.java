package com.nonight.timepickerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nonight.timepickerview.R;
import com.nonight.timepickview.entity.DateStyle;
import com.nonight.timepickview.utils.DateUtils;
import com.nonight.timepickview.view.TimePickerView;

import java.util.Date;

public class MainActivity extends Activity {
    TimePickerView timePickerView;
    TextView show_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePickerView = (TimePickerView) findViewById(R.id.timep);
        show_tv = (TextView) findViewById(R.id.show_tv);
        show_tv.setText(DateUtils.DateToString(timePickerView.getDate(), DateStyle.MM_DD_HH_MM_SS_CN));
        timePickerView.setOnValueChangeListener(new TimePickerView.OnValueChangeListener() {
            @Override
            public void onChange(Date date) {
                show_tv.setText(DateUtils.DateToString(date, DateStyle.MM_DD_HH_MM_SS_CN));
            }
        });
    }
}
