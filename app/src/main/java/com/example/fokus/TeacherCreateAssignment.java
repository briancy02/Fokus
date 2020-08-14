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

import java.util.ArrayList;
import java.util.List;

public class TeacherCreateAssignment extends AppCompatActivity{
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;
    QuestionArrayAdapterTeacher adapter;

    ListView lvQuestionListTeacher;
    Button btnCreateAssignment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_assignment);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        btnCreateAssignment = findViewById(R.id.btnCreateAssignment);

        lvQuestionListTeacher = findViewById(R.id.LvQuestionListTeacher);

        final String youtubeURL = getIntent().getStringExtra("youtubeURL");

        String whereClause = "youtubeLink = '" + youtubeURL + "' and teacherEmail = '" + Backendless.UserService.CurrentUser().getEmail() + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);
        System.out.println(whereClause);

        showProgress(true);
        tvLoad.setText("Getting all assignments... please wait...");

        Backendless.Persistence.of(Assignment.class).find(queryBuilder, new AsyncCallback<List<Assignment>>() {
            @Override
            public void handleResponse(List<Assignment> response) {
                String whereClause2 = "YoutubeURL = '" + youtubeURL + "'";
                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause(whereClause2);
                System.out.println(whereClause2);

                ApplicationClass.assignments = response;


                Backendless.Persistence.of(Question.class).find(queryBuilder, new AsyncCallback<List<Question>>() {
                    @Override
                    public void handleResponse(List<Question> response) {
                        ApplicationClass.questions = response;
//                        for(int i = 0; i < ApplicationClass.assignments.size(); i++){
////                            ApplicationClass.assignments.get(i).setQuestion(ApplicationClass.questions.get(0).getQuestion());
////                            ApplicationClass.assignments.get(i).setAnswerCorrect(ApplicationClass.questions.get(0).getAnswerCorrect());
////                        }
                        System.out.println(ApplicationClass.questions.get(0).getQuestion());

                        String question = ApplicationClass.questions.get(0).getQuestion();
                        String[] questionArray = question.split("//");
                        String answerCorrect = ApplicationClass.questions.get(0).getAnswerCorrect();
                        String[] answerCorrectArray = answerCorrect.split("//");

                        ArrayList<Response> responseArrayList = new ArrayList<Response>();

                        for(int i = 0; i < answerCorrectArray.length; i++){
                            Response responseObject = new Response(questionArray[i], answerCorrectArray[i]);
                            responseArrayList.add(responseObject);

                        }

                        ApplicationClass.responses = responseArrayList;
                        adapter = new QuestionArrayAdapterTeacher( TeacherCreateAssignment.this, ApplicationClass.responses);
                        lvQuestionListTeacher.setAdapter(adapter);
                        showProgress(false);
                        btnCreateAssignment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TeacherCreateAssignment.this.finish();
                            }
                        });


                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(TeacherCreateAssignment.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                        showProgress(false);

                    }
                });

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(TeacherCreateAssignment.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

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
