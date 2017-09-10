package com.example.android.miwok;

import android.content.Context;

/**
 * Created by Alvin Dizon on 10/09/2017.
 */

public class Word {
    //Miwok translation of word
    private String miwokTranslation;

    // Default language translation
    private String defaultTranslation;

    /**
     * Constructs a new Word class
     */
    public Word(Context context) {
        miwokTranslation = "";
        defaultTranslation = "";

    }

    /**
     *  Sets the Miwok Translation
     * @param text is the updated Miwok translation
     */
    public void setMiwokTranslation(String text) {
        miwokTranslation = text;
    }

    /**
     *  Sets the default translation
     * @param text is the updated default translation
     */
    public void setDefaultTranslation(String text) {
        defaultTranslation = text;
    }

    /**
     *  Gets the default translation
     * @return current default translation
     */
    public String getDefaultTranslation(){
        return defaultTranslation;
    }

    /**
     *  Gets the Miwok translation
     * @return current Miwok translation
     */
    public String getMiwokTranslation(){
        return miwokTranslation;
    }

}
