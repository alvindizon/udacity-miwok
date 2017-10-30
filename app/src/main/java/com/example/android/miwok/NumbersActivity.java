package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private static final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("one", R.raw.number_one, "lutti", R.drawable.number_one));
        words.add(new Word("two", R.raw.number_two, "otiiko", R.drawable.number_two));
        words.add(new Word("three", R.raw.number_three, "tolookosu", R.drawable.number_three));
        words.add(new Word("four", R.raw.number_four, "oyyisa", R.drawable.number_four));
        words.add(new Word("five", R.raw.number_five, "massokka", R.drawable.number_five));
        words.add(new Word("six", R.raw.number_six, "temmokka", R.drawable.number_six));
        words.add(new Word("seven", R.raw.number_seven, "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", R.raw.number_eight, "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", R.raw.number_nine, "wo’e", R.drawable.number_nine));
        words.add(new Word("ten", R.raw.number_ten, "na’aacha", R.drawable.number_ten));


        // Create a WordAdapter, whose source is a list of Word objects
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        /** connect the adapter to a gridView object
         * this object should be specified by an ID*
         */
        ListView listView = (ListView) findViewById(R.id.list);

        /**call the setAdapter method on the listView and pass the adapter as the argument
         * this will convert the values on the Arraylist
         * into a view and then the result will be assigned
         * as the value of a textView (simple_list_item_1)
        */
        listView.setAdapter(adapter);

        // this listener will wait for a click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // get Word object at current position
                Word currentWord = (Word) adapterView.getItemAtPosition(i);
                MediaPlayer audioPlayer;
                // create mediaplayer object and set the corresponding audio file as media source
                audioPlayer = MediaPlayer.create(NumbersActivity.this,
                        currentWord.getAudioFileName());
                // start playing the audio file
                audioPlayer.start();
            }
        });

    }
}
