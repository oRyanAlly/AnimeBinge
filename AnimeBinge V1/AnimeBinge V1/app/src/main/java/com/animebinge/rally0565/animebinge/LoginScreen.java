package com.animebinge.rally0565.animebinge;

import android.content.Intent;
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

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvResetPassword);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                btnLogin.setVisibility(View.GONE);
                tvForgotPassword.setVisibility(View.GONE);
                String sPassword = etPassword.getText().toString().trim();

                if(!isValidEmail(etEmail.getText())) {
                    etEmail.setError("Please enter a valid email address");
                }
                if(sPassword.length() < 8) {
                    etPassword.setError("Please enter a password greater than 8 characters");
                }
                if(etEmail.getError() == null || etPassword.getError() == null) {
                    btnLogin.setVisibility(View.VISIBLE);
                    tvForgotPassword.setVisibility(View.VISIBLE);
                    etEmail.setText("");
                    etPassword.setText("");
                }

            }
        });
        tvForgotPassword.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent iForgotPassword = new Intent(LoginScreen.this, ResetPassword.class);
                startActivity(iForgotPassword);
            }
        });
    }

    // When the Login Button is clicked,
    //Verify that the user has entered a valid email
    // and strong password in order to login successfully
    @Override
    public void onClick(View v) {
        String sPassword = etPassword.getText().toString().trim();
        if (!isValidEmail(etEmail.getText())) {
            etEmail.setError("Invalid E-mail");
        }
        if(sPassword.length() < 8) {
            etPassword.setError("Invalid Password");
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
