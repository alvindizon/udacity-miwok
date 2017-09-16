package com.example.android.miwok;

/**
 * Created by Alvin Dizon on 16/09/2017.
 */

public class ReportCard {

    private String mStudentName;
    private int mScienceGrade;
    private int mEnglishGrade;
    private int mMathGrade;

    /**
     * Constructor for ReportCard class
     * @param name      stands for the student's name
     */
    public ReportCard(String name, int scienceGrade, int englishGrade, int mathGrade){
        mStudentName = name;
        mScienceGrade = scienceGrade;
        mEnglishGrade = englishGrade;
        mMathGrade = mathGrade;
    }

    @Override
    public String toString() {
        String reportString = "Student name: " + mStudentName;
        reportString += "\n" + "Science grade: " + mScienceGrade;
        reportString += "\n" + "English grade: " + mEnglishGrade;
        reportString += "\n" + "Math grade: " + mMathGrade;
        return reportString;
    }
}
