/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // create a new object instance of the event listener
        NumbersClickListener clickListener = new NumbersClickListener();

        // Find the view where we want to listen for clicks
        TextView numbersTextView = (TextView)findViewById(R.id.numbers);

        //attach the listener to a view that we want to listen clicks for
        numbersTextView.setOnClickListener(clickListener);
    }

    /**
     * This method displays the Phrases screen if the Phrases button is clicked.
     */
    public void openPhrasesList(View view){
        // executed in an Activity, so 'this' is the context
        Intent i = new Intent(this, PhrasesActivity.class);
        startActivity(i);
    }

    /**
     * This method displays the Family screen if the Family button is clicked.
     */
    public void openFamilyList(View view){
        // executed in an Activity, so 'this' is the context
        Intent i = new Intent(this, FamilyActivity.class);
        startActivity(i);
    }

    /**
     * This method displays the Colors screen if the Colors button is clicked.
     */
    public void openColorsList(View view){
        // executed in an Activity, so 'this' is the context
        Intent i = new Intent(this, ColorsActivity.class);
        startActivity(i);
    }


}
