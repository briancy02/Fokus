package com.example.fokus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.Map;

public class Register extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etName, etMail, etPassword, etConfirmPassword;
    Button btnRegisterTeacher, btnRegisterStudent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);

        btnRegisterTeacher = findViewById(R.id.btnRegisterTeacher);
        btnRegisterStudent = findViewById(R.id.btnRegisterStudent);

        btnRegisterTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() || etConfirmPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(etPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim()))
                    {
                        String name = etName.getText().toString().trim();
                        String email = etMail.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();

                        final BackendlessUser user = new BackendlessUser();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setProperty("title", "teacher");
                        // there are many set properties where we need to input name, teacher or student info etc.
                        user.setProperty("name", name);

                        Backendless.initApp( getApplicationContext(),"BC11B438-4778-DDBA-FF6C-BFECE07FA900", "6CC41866-9B97-4E73-B787-328D1DABA6E7" );
                        Teacher teacher = new Teacher();
                        teacher.setName(name);
                        teacher.setUser(user);
                        teacher.setMail(email);
                        showProgress(true);
                        // assign user to teacher object
                        // This is where I created the relationship
                        user.setProperty("teacher", teacher);
                        // save teacher object to backendless
                        Backendless.Data.of( Teacher.class).save( teacher, new AsyncCallback<Teacher>() {
                            public void handleResponse( Teacher response )
                            {
                                // new Contact instance has been saved
                                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser response) {
                                        showProgress(false);
                                        Toast.makeText(Register.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                                        Register.this.finish();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        showProgress(false);

                                    }
                                });
                            }

                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                                Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(Register.this, "Please make sure that your pass word and re-typed password is the same", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnRegisterStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty() || etMail.getText().toString().isEmpty() ||
                        etPassword.getText().toString().isEmpty() || etConfirmPassword.getText().toString().isEmpty())
                {
                    Toast.makeText(Register.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(etPassword.getText().toString().trim().equals(etConfirmPassword.getText().toString().trim()))
                    {
                        String name = etName.getText().toString().trim();
                        String email = etMail.getText().toString().trim();
                        String password = etPassword.getText().toString().trim();

                        final BackendlessUser user = new BackendlessUser();
                        user.setEmail(email);
                        user.setPassword(password);
                        // there are many set properties where we need to input name, teacher or student info etc.
                        user.setProperty("name", name);
                        user.setProperty("title", "student");

                        showProgress(true);
                        Backendless.initApp( getApplicationContext(),"BC11B438-4778-DDBA-FF6C-BFECE07FA900", "6CC41866-9B97-4E73-B787-328D1DABA6E7" );

                        Student student = new Student();
                        student.setName(name);
                        student.setUser(user);
                        student.setMail(email);
                        user.setProperty("student", student);
                        Backendless.Data.of( Student.class ).save( student, new AsyncCallback<Student>() {
                            public void handleResponse( Student response )
                            {
                                // new User instance has been saved
                                Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                                    @Override
                                    public void handleResponse(BackendlessUser response) {
                                        showProgress(false);
                                        Toast.makeText(Register.this, "User successfully registered", Toast.LENGTH_SHORT).show();
                                        Register.this.finish();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                        showProgress(false);

                                    }
                                });
                            }

                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                                Toast.makeText(Register.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(Register.this, "Please make sure that your pass word and re-typed password is the same", Toast.LENGTH_SHORT).show();
                    }

                }
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
