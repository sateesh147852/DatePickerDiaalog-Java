package com.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar;
    private int type;
    private final int year;
    private final int month;
    private final int day;
    private final onDateSelected dateSelected;
    private final SimpleDateFormat simpleDateFormat;

    public DatePickerDialogFragment(onDateSelected dateSelected){
        this.dateSelected = dateSelected;
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        simpleDateFormat = new SimpleDateFormat("YYYYmmdd");
    }

    /**
     *  1  normal
     *  2  Above 18 age
     *  3  current to  future
     *  4  past to current
     */
    public void setType(int type){
        this.type = type;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DatePickerDialog dialog = new DatePickerDialog(getContext(),this,year,month,day);
        calendar = Calendar.getInstance();

        switch (type){

            case 2:
                calendar.add(Calendar.YEAR,-18);
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                break;

            case 3:
                dialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
                break;

            case 4:
                dialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                break;

        }

        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        try {
            Date date = simpleDateFormat.parse(year+""+month+1+""+dayOfMonth);
            calendar.setTime(date);
            dateSelected.onDateSelected(calendar.getTimeInMillis(),year,month+1,dayOfMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    interface onDateSelected{
        void onDateSelected(long milliseconds, int year, int month, int dayOfMonth);
    }
}
