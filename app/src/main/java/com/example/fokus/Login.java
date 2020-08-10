package com.example.fokus;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
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

public class Login extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etMail, etPassword;
    Button btnLogin, btnRegister, btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        etMail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegisterTeacher);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        // When Login button is clicked...
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Instruct to fill in all fields if fields are empty
                if(etMail.getText().toString().isEmpty() || etMail.getText().toString().isEmpty())
                {
                    Toast.makeText(Login.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    // Save input data and verify whether the person logged in exists in the backendless database
                    String email = etMail.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();

                    showProgress(true);

                    Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {
                            // if login was successful return to main activity
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                            if(Backendless.UserService.CurrentUser().getProperty("title").equals("teacher")){
                                startActivity(new Intent(Login.this, StudentMain.class));
                            }
                            else{
                                startActivity(new Intent(Login.this, TeacherMain.class));
                            }
                            Login.this.finish();



                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(Login.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
                            showProgress(false);

                        }
                    }, true);

                }




            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));

            }
        });

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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