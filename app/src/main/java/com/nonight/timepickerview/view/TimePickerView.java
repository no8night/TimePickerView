package com.nonight.timepickerview.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.nonight.timepickerview.utils.DateUtils;
import com.nonight.timepickerview.R;

import java.util.Date;

/**
 * Created by 祈愿星痕 on 2017/5/10.
 */

public class TimePickerView extends LinearLayout {
    Context context;
    TextView date_tv;
    NumberPicker minutepicker,amorpmpicker,hourpicker;
    Date date;
    public TimePickerView(Context context) {
        super(context);
        this.context = context;
        initview();
    }

    public TimePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initview();
    }

    public TimePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initview();
    }

    private void initview(){
        LayoutInflater.from(context).inflate(R.layout.timepickerview, this, true);
        minutepicker = (NumberPicker) findViewById(R.id.window_date_miniute_np);
        amorpmpicker = (NumberPicker) findViewById(R.id.window_date_amorpm_np);
        hourpicker = (NumberPicker) findViewById(R.id.window_date_hour_np);
        date_tv = (TextView) findViewById(R.id.data_tv);
        if (date == null){
            date = new Date(System.currentTimeMillis());
        }
        date_tv.setText(DateUtils.getYear(date) + "-" + (DateUtils.getMonth(date)+1)  + "-" + DateUtils.getDay(date));
        date_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                createdatedialog(context,date_tv,date);
            }
        });


    }
    public  Date getDate(){
        return date;
    }
    public  void SetDate(Date date){
        this.date = date;
        date_tv.setText(DateUtils.getYear(date) + "-" + (DateUtils.getMonth(date)+1)  + "-" + DateUtils.getDay(date));
    }
    protected void createdatedialog(Context activity,final TextView date_tv, final Date date) {


        // 初始化DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date_tv.setText(year + "-" + (monthOfYear+1)  + "-" + dayOfMonth);
                DateUtils.setYear(date,year);
                DateUtils.setMonth(date,monthOfYear);
                DateUtils.setDay(date,dayOfMonth);
            }

        }, DateUtils.getYear(date), DateUtils.getMonth(date), DateUtils.getDay(date));
        datePickerDialog.show();
    }
}
