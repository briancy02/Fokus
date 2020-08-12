package com.example.fokus;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.Persistence;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.util.JSONUtil;

import java.util.ArrayList;
import java.util.List;

public class NewAssignment extends AppCompatActivity{
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    Button btnSubmitT;
    EditText etAssignmentName, etDueDate, etDescription, etYoutubeURL;
    VideoView v2;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assign);
        btnSubmitT = findViewById(R.id.btnSubmitT);
        etAssignmentName = findViewById(R.id.etAssignmentName);
        etDueDate = findViewById(R.id.etDueDate);
        etDescription = findViewById(R.id.etDescription);
        etYoutubeURL = findViewById(R.id.etYoutubeURL);
        v2 = findViewById(R.id.videoView2);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        btnSubmitT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAssignmentName.getText().toString().isEmpty() || etDueDate.getText().toString().isEmpty() ||
                        etDescription.getText().toString().isEmpty() || etYoutubeURL.getText().toString().isEmpty())
                {
                    Toast.makeText(NewAssignment.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    String assignmentName = etAssignmentName.getText().toString().trim();
                    String dueDate = etDueDate.getText().toString().trim();
                    String description = etDescription.getText().toString().trim();
                    String youtubeURL = etYoutubeURL.getText().toString().trim();

                    Teacher teacher = (Teacher) Backendless.UserService.CurrentUser().getProperty("teacher");
                    ArrayList<Student> students = new ArrayList<Student>();
                    ArrayList<Assignment> assignments = new ArrayList<Assignment>();
                    System.out.println(Backendless.UserService.CurrentUser().getProperty("title"));
                    //System.out.println(teacher.mail);
                    //Object[] studentsObjectArray = (Object[]) Backendless.UserService.CurrentUser().getProperty("students");
                    //Student[] studentsArray;
                    String studentEmail = String.valueOf(Backendless.UserService.CurrentUser().getProperty("studentEmail"));
                    System.out.println(studentEmail);
                    System.out.println(Backendless.UserService.CurrentUser().getProperty("studentEmail"));

                    String[] studentEmailArray = studentEmail.split(",");
                    for(int i = 0; i < studentEmailArray.length; i++){
                        Assignment assignment = new Assignment();
                        assignment.setAssignmentName(assignmentName);
                        assignment.setDueDate(dueDate);
                        assignment.setDescription(description);
                        assignment.setYoutubeLink(youtubeURL);
                        assignment.setTeacher(teacher);
                        assignment.setStudentEmail(studentEmail);
                        assignments.add(assignment);

                    }

                    Backendless.Data.of(Assignment.class).create(assignments, new AsyncCallback<List<String>>() {
                        @Override
                        public void handleResponse(List<String> ids) {
                            for (String id : ids) {
                                Log.i("NewAssignment", "Order object has been saved with ID - " + id);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e("NewAssignment", fault.getMessage());
                        }
                    });


                    //System.out.println(studentsObjectArray[0]);

                    // if array is not empty, it can be cast to an array of specific type
//                    if (studentsObjectArray != null && studentsObjectArray.length > 0) {
//                        studentsArray = (Student[]) studentsObjectArray;
//                        System.out.println("in loop");
//                        for(int i = 0; i < studentsArray.length; i++) {
//
//                            Assignment assignment = new Assignment();
//                            assignment.setAssignmentName(assignmentName);
//                            assignment.setDueDate(dueDate);
//                            assignment.setDescription(description);
//                            assignment.setYoutubeLink(youtubeURL);
//                            assignment.setTeacher(teacher);
//                            assignment.setStudent(studentsArray[i]);
//                            assignments.add(assignment);
//                        }
//                    }
//
//                    //Object[] assignmentsArray = assignments.toArray();
//                    System.out.println("out of loop");
//                    System.out.println(assignments.get(0));
//                    Backendless.Data.of(Assignment.class).create(assignments, new AsyncCallback<List<String>>() {
//
//                        @Override
//                        public void handleResponse(List<String> ids) {
//                            for (String id : ids) {
//                                Log.i("NewAssignment", "Order object has been saved with ID - " + id);
//                            }
//                        }
//
//                        @Override
//                        public void handleFault(BackendlessFault fault) {
//                            Log.e("NewAssignment", fault.getMessage());
//                        }
//                    });


                }
            }
        });

    }
}
