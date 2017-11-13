package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

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


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // create AudioManager object by calling the enclosing Activity's system service
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        /** connect the adapter to a listView object
         * this object should be specified by an ID*
         */
        ListView listView = (ListView) rootView.findViewById(R.id.list);

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
                    audioPlayer = MediaPlayer.create(getActivity(),
                            currentWord.getAudioFileName());
                    // start playing the audio file
                    audioPlayer.start();
                    // setup completion listener
                    audioPlayer.setOnCompletionListener(mAudioPlayerListener);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        // release allocated memory once audio finishes playing
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
