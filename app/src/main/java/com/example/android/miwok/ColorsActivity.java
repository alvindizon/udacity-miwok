package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // fragment is inserted into activity_category xml using FragmentTransaction
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorsFragment())
                .commit();
    }
}
