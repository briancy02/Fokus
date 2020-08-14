package com.example.fokus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class StudentViewAssignment extends AppCompatActivity{
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    QuestionArrayAdapter adapter;

    ListView lvQuestionList;


    Button btnSubmitAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_assignment);


//        btnDueDate = (Button) findViewById(R.id.btnDueDate);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        lvQuestionList = findViewById(R.id.LvQuestionList);
        btnSubmitAssignment = findViewById(R.id.btnSubmitAssignment);



        final int index = getIntent().getIntExtra("index", 0);

        final Assignment assignmentObject = ApplicationClass.assignments.get(index);

        String question = ApplicationClass.assignments.get(index).question;
        System.out.println(question);
        final String[] questionArray = question.split("//");
        final String answerCorrect = ApplicationClass.assignments.get(index).answerCorrect;
        System.out.println(answerCorrect);
        final String[] answerCorrectArray = answerCorrect.split("//");
        System.out.println(answerCorrectArray.length);

        ArrayList<Response> responseArrayList = new ArrayList<Response>();
        for(int i = 0; i < answerCorrectArray.length; i++){
            // works
            Response response = new Response(questionArray[i], answerCorrectArray[i]);
            responseArrayList.add(response);
            ApplicationClass.responses.add(response);
        }
        //ApplicationClass.responses = responseArrayList;
        System.out.println(responseArrayList.get(0).getAnswerCorrect());
        System.out.println(ApplicationClass.responses.get(0).getAnswerStudent());
        adapter = new QuestionArrayAdapter( StudentViewAssignment.this, ApplicationClass.responses);
        lvQuestionList.setAdapter(adapter);

        // put all answers in responses object list
        int rawScore = 0;
        System.out.println(answerCorrectArray.length);
        System.out.println(ApplicationClass.responses.get(0).getAnswerStudent());


        for(int i = 0; i < answerCorrectArray.length; i++){
            if(ApplicationClass.responses.get(i).getAnswerStudent().equals(ApplicationClass.responses.get(i).getAnswerCorrect())){
                System.out.println(ApplicationClass.responses.get(i).getAnswerStudent());
                rawScore++;
            }
        }

        String score = rawScore + " / " + questionArray.length;
        assignmentObject.setScore(score);
        // probably need a screen that just displays the student's score.
        // now get started with student score list view for teachers using another adapter view

        showProgress(false);
        System.out.println(score);



        btnSubmitAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress(true);

                StudentViewAssignment.this.finish();


            }
        });





    }
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}