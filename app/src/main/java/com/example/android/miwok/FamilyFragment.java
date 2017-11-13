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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

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
            new AudioManager.OnAudioFocusChangeListener() {
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


    public FamilyFragment() {
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
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        /** connect the adapter to a gridView object
         * this object should be specified by an ID*
         * rootView contains child views such as listView
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
            audioPlayer = null;
            // abandon audio focus and unregister audio focus listener,
            // so we don't get callbacks anymore
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }

}
