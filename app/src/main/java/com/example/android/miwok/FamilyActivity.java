package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private static final String TAG = "FamilyActivity";

    // variable for audio playback
    private MediaPlayer audioPlayer;

    private MediaPlayer.OnCompletionListener mAudioPlayerListener =
            new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("father", R.raw.family_father, "әpә", R.drawable.family_father));
        words.add(new Word("mother", R.raw.family_mother, "әṭa", R.drawable.family_mother));
        words.add(new Word("son", R.raw.family_son, "angsi", R.drawable.family_son));
        words.add(new Word("daughter", R.raw.family_daughter, "tune", R.drawable.family_daughter));
        words.add(new Word("older brother", R.raw.family_older_brother, "taachi", R.drawable.family_older_brother));
        words.add(new Word("younger brother", R.raw.family_younger_brother, "chalitti", R.drawable.family_younger_brother));
        words.add(new Word("older sister", R.raw.family_older_sister, "teṭe", R.drawable.family_older_sister));
        words.add(new Word("younger sister", R.raw.family_younger_sister, "kolliti", R.drawable.family_younger_sister));
        words.add(new Word("grandmother", R.raw.family_grandmother, "ama", R.drawable.family_grandmother));
        words.add(new Word("grandfather", R.raw.family_grandfather, "paapa", R.drawable.family_grandfather));


        // Create a WordAdapter, whose source is a list of Word objects
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

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

                // call Helper method to release allocated memory before creating new MediaPlayer
                releaseMediaPlayer();

                // create mediaplayer object and set the corresponding audio file as media source
                audioPlayer = MediaPlayer.create(FamilyActivity.this,
                        currentWord.getAudioFileName());
                // start playing the audio file
                audioPlayer.start();

                // setup completion listener
                audioPlayer.setOnCompletionListener(mAudioPlayerListener);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Helper method that releases allocated memory
     */
    private void releaseMediaPlayer(){
        if(audioPlayer != null){
            // Release memory resources allocated for the MediaPlayer object
            audioPlayer.release();
            audioPlayer = null;
        }
    }
}
