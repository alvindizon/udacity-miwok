package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private static final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // create word arraylist
        ArrayList<String> words = new ArrayList<>();
        // add elements in Arraylist
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");

        // print out each element of the word arraylist to logcat
        for(int i = 0; i < words.size(); i++){
            Log.v(TAG, "Word at index " + i + ": " + words.get(i));
        }

    }
}
