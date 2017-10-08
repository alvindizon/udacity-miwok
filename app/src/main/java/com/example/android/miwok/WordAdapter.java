package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alvin Dizon on 12/09/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private static final String TAG = "WordAdapter";


    /**
     *   constructor adapted from ArrayAdapter (Context context, int resource,T[] objects)
     *   we don't need the second arg (since it refers to a single textview resource ID)
     *   so we set it to zero
     * @param context Used to inflate layout file
     * @param words  List of Word objects to display in a  list
     */
    public WordAdapter(Activity context, ArrayList<Word> words){
        super(context, 0, words);
    }

    /**
     * Manually handles getting the view to be reused, and then
     * displays the specified view
     *
     * @param position       Position in the list of data objects  that should be displayed
     *                       in the view
     * @param convertView    View to be reused and inflated
     * @param parent         parent ViewGroup
     * @return               the view that will be used by the AdapterView(listview, gridview, etc)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // check if view is being reused
        // if not, inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the current word object associated with specified position in the list
        Word currentWord = getItem(position);

        // find the textview for the default textview
        TextView defaultTranslationText =
                (TextView) listItemView.findViewById(R.id.default_text_view);
        // get the default translation from the current word object
        // set this text on the default translation text view
        defaultTranslationText.setText(currentWord.getDefaultTranslation());

        // do the same for the miwok textview and set the miwok word
        TextView miwokTranslationText =
                (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTranslationText.setText(currentWord.getMiwokTranslation());

        //Find the ImageView in the list_item.xml layout
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image);

        // check if current Word has image to be displayed
        if(currentWord.hasImage()){
            // display image using resource ID
            iconView.setImageResource(currentWord.getImageResourceId());

            // set Visibility to VISIBLE since previous setting might be GONE
            iconView.setVisibility(View.VISIBLE);
        }
        else{
            // if no image has been provided, set ImageView visibility to GONE
            // GONE: ImageView invisible, will not take up layout space
            // INVISIBLE: ImageView not visible but will still take up space
            iconView.setVisibility(View.GONE);
        }


        // return the whole layout so ListView can display it
        return listItemView;
    }
}
