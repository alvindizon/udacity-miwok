package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create ArrayList of ReportCard objects
        ArrayList<ReportCard> grades = new ArrayList<>();
        grades.add(new ReportCard("Joyce", 100, 100, 100));
        grades.add(new ReportCard("Alvin", 100, 100, 100));

        // create a ReportCard adapter
        ReportCardAdapter reportCardAdapter = new ReportCardAdapter(this, grades);

        // connect adapter to a listview object
        ListView listView = (ListView) findViewById(R.id.list);

        // call setAdapter method and pass reportCardAdapter as input arg
        listView.setAdapter(reportCardAdapter);
    }
}
