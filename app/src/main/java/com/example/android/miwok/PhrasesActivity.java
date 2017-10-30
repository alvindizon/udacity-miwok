package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private static final String TAG = "PhrasesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", R.raw.phrase_where_are_you_going, "minto wuksus"));
        words.add(new Word("What is your name?", R.raw.phrase_what_is_your_name, "tinnә oyaase'nә"));
        words.add(new Word("My name is...", R.raw.phrase_my_name_is, "oyaaset..."));
        words.add(new Word("How are you feeling?", R.raw.phrase_how_are_you_feeling, "michәksәs?"));
        words.add(new Word("I'm feeling good.", R.raw.phrase_im_feeling_good, "kuchi achit"));
        words.add(new Word("Are you coming?", R.raw.phrase_are_you_coming, "әәnәs'aa?"));
        words.add(new Word("Yes, I'm coming.", R.raw.phrase_yes_im_coming, "hәә’ әәnәm"));
        words.add(new Word("I'm coming.", R.raw.phrase_im_coming, "әәnәm"));
        words.add(new Word("Let's go.", R.raw.phrase_lets_go,"yoowutis"));
        words.add(new Word("Come here.", R.raw.phrase_come_here, "әnni'nem"));


        // Create a WordAdapter, whose source is a list of Word objects
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

        /** connect the adapter to a listView object
         * this object should be specified by an ID*
         */
        ListView listView = (ListView) findViewById(R.id.list);


        /**call the setAdapter method on the listView and pass the adapter as the argument
         * this will convert the values on the Arraylist
         * into a view and then the result will be assigned
         * as the value of a textView (simple_list_item_1)
         */
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = (Word) adapterView.getItemAtPosition(i);
                MediaPlayer audioPlayer;
                audioPlayer = MediaPlayer.create(PhrasesActivity.this,
                            currentWord.getAudioFileName());
                audioPlayer.start();

            }
        });

    }
}
