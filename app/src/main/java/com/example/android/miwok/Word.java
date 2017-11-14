package com.example.android.miwok;

import android.content.Context;

/**
 * Created by Alvin Dizon on 10/09/2017.
 */

public class Word {
    //Miwok translation of word
    private String mMiwokTranslation;

    // Default language translation
    private String mDefaultTranslation;

    // value that represents no image was provided
    private static final int NO_IMAGE_PROVIDED = -1;

    //image resource id
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    // Audio filename for word
    private int mAudioFileName;


    /**
     *  Constructor for new Word class
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (e.g., English)
     * @param audioFileName is the audio file for each Miwok word
     * @param miwokTranslation is equivalent word in the Miwok language
     */
    public Word(String defaultTranslation, int audioFileName, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mAudioFileName = audioFileName;
        mDefaultTranslation = defaultTranslation;
    }

    /**
     * Constructor for modified Word class
     * Used by FamilyFragment, ColorsFragment, and NumbersFragment
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (e.g., English)
     * @param audioFileName is the audio file for each Miwok word
     * @param miwokTranslation is equivalent word in the Miwok language
     * @param imageResourceId is the resource ID of the image to be displayed beside the words
     */
    public Word(String defaultTranslation,  int audioFileName, String miwokTranslation,
                int imageResourceId)
    {
        mMiwokTranslation = miwokTranslation;
        mAudioFileName = audioFileName;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     *  Gets the default translation
     * @return current default translation
     */
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    /**
     *  Gets the Miwok translation
     * @return current Miwok translation
     */
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    /**
     *  Gets the image resource ID
     * @return current image resource ID
     */
    public int getImageResourceId(){
        return mImageResourceId;
    }

    /**
     *  Returns the filename of the audio file associated with the word
     * @return current Miwok audio filename
     */
    public int getAudioFileName(){
        return mAudioFileName;
    }

    /**
     *  Tells us whether or not an image is set to be displayed
     * @return TRUE if mImageResourceId has value other than -1,
     *          FALSE if mImageResourceId remains -1
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mAudioFileName=" + mAudioFileName +
                '}';
    }
}
