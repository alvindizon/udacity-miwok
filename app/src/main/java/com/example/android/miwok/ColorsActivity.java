package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private static final String TAG = "ColorsActivity";

    // variable for audio playback
    private MediaPlayer audioPlayer;

    // AudioManager is responsible for audio focus
    private AudioManager audioManager;

    // this listener is triggered when playback finishes
    private MediaPlayer.OnCompletionListener mAudioPlayerListener =
            new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    // this listener is triggered when audio focus changes
    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                // Resume playing audio file
                audioPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                // stop MediaPlayer
                audioPlayer.stop();
                // release resources
                releaseMediaPlayer();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // pause MediaPlayer
                audioPlayer.pause();
                // play audio from beginning once audio resumes so pronunciation is heard in full
                audioPlayer.seekTo(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // create AudioManager object
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // create an ArrayList of Word objects
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("red", R.raw.color_red, "weṭeṭṭi", R.drawable.color_red));
        words.add(new Word("green", R.raw.color_green, "chokokki", R.drawable.color_green));
        words.add(new Word("brown", R.raw.color_brown, "ṭakaakki", R.drawable.color_brown));
        words.add(new Word("gray", R.raw.color_gray, "ṭopoppi", R.drawable.color_gray));
        words.add(new Word("black", R.raw.color_black, "kululli", R.drawable.color_black));
        words.add(new Word("white", R.raw.color_white, "kelelli", R.drawable.color_white));
        words.add(new Word("dusty yellow", R.raw.color_dusty_yellow, "ṭopiisә", R.drawable.color_dusty_yellow));
        words.add(new Word("mustard yellow", R.raw.color_mustard_yellow, "chiwiiṭә", R.drawable.color_mustard_yellow));

        // Create a WordAdapter, whose source is a list of Word objects
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);

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

                // request Audio focus. if focus has been granted then continue with audio playback
                int result = audioManager.requestAudioFocus(afChangeListener,
                        //Use music stream
                        AudioManager.STREAM_MUSIC,
                        // set audio focus to last for only a short duration
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
                );

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // create mediaplayer object and set the corresponding audio file as media source
                    audioPlayer = MediaPlayer.create(ColorsActivity.this,
                            currentWord.getAudioFileName());
                    // start playing the audio file
                    audioPlayer.start();
                    // setup completion listener
                    audioPlayer.setOnCompletionListener(mAudioPlayerListener);
                }
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
            // Set object to null, so we can tell if audioPlayer has been setup or not
            audioPlayer = null;
            // abandon audio focus and unregister audio focus listener,
            // so we don't get callbacks anymore
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
