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

}
