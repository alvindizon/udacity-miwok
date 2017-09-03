package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NumbersActivity extends AppCompatActivity {
    private static final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // create word array
        String[] words = {"one", "two", "three", "four", "five",
                    "six", "seven", "eight", "nine", "ten"};

        // print out each element of the word array to logcat
        for(int i = 0; i < words.length; i++){
            Log.v(TAG, "Word at index " + i + ": " + words[i]);
        }

    }
}
