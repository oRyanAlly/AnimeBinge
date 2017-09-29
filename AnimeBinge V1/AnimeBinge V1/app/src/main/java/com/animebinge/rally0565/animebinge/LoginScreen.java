/*
 * Programmer: Ryan Ally
 * Course: PROG3210
 * Student ID: 7260565
 * Assignment 1: AnimeBinge
 */


package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnSignUp;
    private EditText etEmail, etPassword;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Define all Widgets
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvResetPassword);

        //Attached Listeners to Specified Buttons
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                //When Sign Up is clicked, hide some of the widgets
                //Rename one, then eprform validation(for now)
                //Then return back to home screen
                        btnLogin.setVisibility(View.GONE);
                        tvForgotPassword.setVisibility(View.GONE);
                        btnSignUp.setText("CREATE ACCOUNT");
                        String sPassword = etPassword.getText().toString();
                        String sEmail = etEmail.getText().toString();

                        if (!isValidEmail(sEmail) || sPassword == null) {
                            etEmail.setError("Please enter a valid email address");
                        }
                        if (sPassword.length() < 8 || sPassword == null) {
                            etPassword.setError("Please enter a password greater than 8 characters");
                        }
                        if (etEmail.getError() == null && etPassword.getError() == null) {
                            btnLogin.setVisibility(View.VISIBLE);
                            tvForgotPassword.setVisibility(View.VISIBLE);
                            btnSignUp.setText("SIGN UP");
                            etEmail.setText("");
                            etPassword.setText("");
                        }
            }
        });
        //Just send the user to the Reset Password page, if the text is clicked.
        tvForgotPassword.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent iResetPassword = new Intent(LoginScreen.this, ResetPassword.class);
                startActivity(iResetPassword);
            }
        });
    }

    // When the Login Button is clicked,
    //Verify that the user has entered a valid email
    // and strong password in order to login successfully
    @Override
    public void onClick(View v) {
        String sPassword = etPassword.getText().toString().trim();
        if (!isValidEmail(etEmail.getText()) || etEmail.getText() == null) {
            etEmail.setError("Invalid E-mail");
        }
        if(sPassword.length() < 8 || etPassword.getText() == null) {
            etPassword.setError("Invalid Password");
        }
        if (etEmail.getError() == null && etPassword.getError() == null) {
            Intent iHomePage = new Intent(this, HomePage.class);
            startActivity(iHomePage);
        }
    }

    //Source: https://stackoverflow.com/questions/22348212/android-check-if-an-email-address-is-valid-or-not
    //Checks to see if a email matches the according pattern
    public final static boolean isValidEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }
}
