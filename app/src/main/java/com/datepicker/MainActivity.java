package com.datepicker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.datepicker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements DatePickerDialogFragment.onDateSelected, View.OnClickListener {

    private ActivityMainBinding binding;
    private DatePickerDialogFragment datePickerDialogFragment;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialize();
    }

    private void initialize() {
        datePickerDialogFragment = new DatePickerDialogFragment(this);
        binding.tvAbove18.setOnClickListener(this);
        binding.tvNormal.setOnClickListener(this);
        binding.tvCurrentToFuture.setOnClickListener(this);
        binding.tvCurrentToPast.setOnClickListener(this);
    }

    @Override
    public void onDateSelected(long milliseconds, int year, int month, int dayOfMonth) {
        Log.i(TAG, "onDateSelected: " + milliseconds + "  " + year + "  " + "  " + month + "  " + dayOfMonth);
        Toast.makeText(this,  year + " / " + month + " / " + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvNormal:
                datePickerDialogFragment.show(getSupportFragmentManager(), "datepicker");
                break;

            case R.id.tvAbove18:
                datePickerDialogFragment.setType(2);
                datePickerDialogFragment.show(getSupportFragmentManager(), "datepicker");
                break;

            case R.id.tvCurrentToFuture:
                datePickerDialogFragment.setType(3);
                datePickerDialogFragment.show(getSupportFragmentManager(), "datepicker");
                break;

            case R.id.tvCurrentToPast:
                datePickerDialogFragment.setType(4);
                datePickerDialogFragment.show(getSupportFragmentManager(), "datepicker");
                break;
        }
    }
}

