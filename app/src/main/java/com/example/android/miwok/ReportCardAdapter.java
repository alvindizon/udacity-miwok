package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alvin Dizon on 17/09/2017.
 */

public class ReportCardAdapter extends ArrayAdapter<ReportCard> {

    /**
     * Constructor based on ArrayAdapter
     * @param context   used to inflate the layout file
     * @param grades    the report card object
     */
    public ReportCardAdapter(Activity context, ArrayList<ReportCard> grades) {
        super(context, 0, grades);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        // check if view is being reused
        // if not, inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.report_card, parent, false);
        }
        // Get the current ReportCard object associated with specified position in the list
        ReportCard currentReportCard = getItem(position);

        // find the textview for the Name
        TextView studentNameTextView =
                (TextView) listItemView.findViewById(R.id.name_text_view);
        // get the student name from the current ReportCard object
        // set this text on the name text view
        studentNameTextView.setText("Name: " + currentReportCard.getStudentName());

        // do the same for the grades
        TextView scienceGradeTextView =
                (TextView) listItemView.findViewById(R.id.science_grade_text_view);
        scienceGradeTextView.setText("Science grade: " + currentReportCard.getScienceGrade());

        TextView englishGradeTextView =
                (TextView) listItemView.findViewById(R.id.english_grade_text_view);
        englishGradeTextView.setText("English grade: " + currentReportCard.getEnglishGrade());

        TextView mathGradeTextView =
                (TextView) listItemView.findViewById(R.id.math_grade_text_view);
        mathGradeTextView.setText("Math grade: " + currentReportCard.getMathGrade());

        // return the whole layout so ListView can display it
        return listItemView;
    }
}
