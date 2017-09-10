package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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


        /**initialize the adapter :
         * 1. specify the type of item to be converted to a view
         * 2. supply the context, XML item layout where the words will be shown, and the data array
         */
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        /** connect the adapter to a gridView object
         * this object should be specified by an ID*
         */
        GridView gridView = (GridView) findViewById(R.id.grid);

        /**call the setAdapter method on the gridView and pass the adapter as the argument
         * this will convert the values on the array into a view and then the result will be assigned
         * as the value of a textView (simple_list_item_1)
        */
        gridView.setAdapter(itemsAdapter);

    }
}
