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

    //image resource id
    private int mImageResourceId;

    /**
     *  Constructor for new Word class
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (e.g., English)
     * @param miwokTranslation is equivalent word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;

    }

    /**
     * Constructor for modified Word class
     * Used by FamilyActivity, ColorsActivity, and NumbersActivity
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (e.g., English)
     * @param miwokTranslation is equivalent word in the Miwok language
     * @param imageResourceId is the resource ID of the image to be displayed beside the words
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId) {
        mMiwokTranslation = miwokTranslation;
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


}
