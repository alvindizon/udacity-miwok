package com.example.android.miwok;

/**
 * Created by Alvin Dizon on 12/08/2017.
 */
import android.view.View;
import android.widget.Toast;

// make explicit that we are using the OnClickListener interface of View package
public class NumbersClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),
                "Open the list of numbers", Toast.LENGTH_SHORT).show();
    }
}
