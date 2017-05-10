package com.nonight.timepickview.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.nonight.timepickerview.R;
import com.nonight.timepickerview.utils.DateUtils;

import java.util.Date;

/**
 * Created by 祈愿星痕 on 2017/5/10.
 */

public class TimePickerView extends LinearLayout {
    Context context;
    TextView date_tv;
    NumberPicker minutepicker,amorpmpicker,hourpicker;
    Date date;
    OnValueChangeListener listener;
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



        date_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                createdatedialog(context,date_tv,date);

            }
        });

        //am pm选择器
        final String[] taskGroupString = { "AM", "PM"};
        amorpmpicker.setDisplayedValues(taskGroupString);
        amorpmpicker.setMinValue(0);
        amorpmpicker.setMaxValue(taskGroupString.length - 1);

        amorpmpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                date.setTime(DateUtils.setHour(DateUtils.getHour(date)+(newVal-oldVal)*12,date).getTime());
                date_tv.setText(DateUtils.getYear(date) + "-" + (DateUtils.getMonth(date)+1)  + "-" + DateUtils.getDay(date));
                if (listener!=null) {
                    listener.onChange(date);
                }
            }
        });


        //小时numberpicker
        hourpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                date.setTime(DateUtils.setHour(newVal+amorpmpicker.getValue()*12,date).getTime());
                date_tv.setText(DateUtils.getYear(date) + "-" + (DateUtils.getMonth(date)+1)  + "-" + DateUtils.getDay(date));
                if (listener!=null) {
                    listener.onChange(date);
                }
            }
        });
        hourpicker.setMaxValue(12);
        hourpicker.setMinValue(1);
        hourpicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //分钟

        minutepicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                if (value<10) {
                    return "0"+value;
                }else {
                    return ""+value;
                }
            }
        });
        minutepicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                String text = "";
                date.setTime(DateUtils.addMinute(date,newVal-oldVal).getTime());
                if (listener!=null) {
                    listener.onChange(date);
                }
            }
        });

        minutepicker.setMaxValue(59);
        minutepicker.setMinValue(0);


        ///初始化
        if (date == null){
            date = new Date(System.currentTimeMillis());

            ///对日期textview
            date_tv.setText(DateUtils.getYear(date) + "-" + (DateUtils.getMonth(date)+1)  + "-" + DateUtils.getDay(date));
            //am ap picker  hour picker
            if (DateUtils.getHour(date)>12){
                amorpmpicker.setValue(1);
                hourpicker.setValue(DateUtils.getHour(date)-12);
            }else {
                amorpmpicker.setValue(0);
                hourpicker.setValue(DateUtils.getHour(date));
            }
            minutepicker.setValue(DateUtils.getMinute(date));

        }
    }
    public  void setOnValueChangeListener(OnValueChangeListener onValueChangeListener){
        this.listener = onValueChangeListener;
    }
    public  Date getDate(){
        return date;
    }
    public  void setDate(Date date){
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
                if (listener!=null) {
                    listener.onChange(date);
                }
            }

        }, DateUtils.getYear(date), DateUtils.getMonth(date), DateUtils.getDay(date));
        datePickerDialog.show();
    }

    public interface OnValueChangeListener{
        void onChange(Date date);
    }
}
